package projects.sahoo.mygraphql.query_builder;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@Getter
@ToString
public class Condition {

    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String SINGLE_QUOTE = "'";
    public static final String SINGLE_QUOTE_TWICE = "''";
    private String columnName;
    private Object value;
    private String tableName;
    private Operator operatorToBeUsed;
    private Check conditionToBeUsed;

    public Condition(String columnName, Object value, String tableName) {
        this.columnName = columnName;
        this.value = value;
        this.tableName = tableName;
        this.operatorToBeUsed = Operator.NONE;
        this.conditionToBeUsed = Check.EQUALS;
    }

    public Condition withOperatorToBeUsed(Operator operatorToBeUsed) {
        this.operatorToBeUsed = operatorToBeUsed;
        return this;
    }

    public Condition withConditionToBeUsed(Check conditionToBeUsed) {
        this.conditionToBeUsed = conditionToBeUsed;
        return this;
    }

    private static String formatSQLValue(Object value) {
        if (value == null) {
            return "NULL";
        }

        String formattedValue;

        switch (value.getClass().getSimpleName()) {
            case "String":
                formattedValue =
                        SINGLE_QUOTE + ((String) value).replace(SINGLE_QUOTE, SINGLE_QUOTE_TWICE)
                                + SINGLE_QUOTE;
                break;
            case "LocalDate":
                formattedValue = SINGLE_QUOTE + ((LocalDate) value)
                        .format(DateTimeFormatter.ofPattern(DATE_PATTERN)) + SINGLE_QUOTE;
                break;
            case "LocalDateTime":
                formattedValue = SINGLE_QUOTE + ((LocalDateTime) value)
                        .format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)) + SINGLE_QUOTE;
                break;
            case "Boolean":
                formattedValue = ((Boolean) value) ? "1" : "0";
                break;
            case "ArrayList":
            case "LinkedList":
            case "HashSet":
                formattedValue = collectionToSQLString((Collection) value);
                break;
            default:
                formattedValue = value.toString();
                break;
        }

        return formattedValue;
    }

    private static String collectionToSQLString(Collection values) {
        if (values.size() == 0) {
            return "NULL";
        }

        StringBuilder strValues = new StringBuilder();
        boolean firstValue = true;
        for (Object value : values) {
            if (firstValue) {
                strValues.append(formatSQLValue(value));
                firstValue = false;
            } else {
                strValues.append(",").append(formatSQLValue(value));
            }
        }
        return strValues.toString();
    }
}
