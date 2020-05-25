package projects.sahoo.mygraphql.query_builder;

import graphql.GraphQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import projects.sahoo.mygraphql.graphql_filter.DataType;
import projects.sahoo.mygraphql.graphql_filter.QueryFilter;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GraphQLQueryUtils {

    private static final Logger logger = LoggerFactory.getLogger(GraphQLQueryUtils.class);

    public static List<Condition> getConditions(List<? extends QueryFilter> filters, Table table) {
        List<Condition> conditions = new LinkedList<>();

        if (CollectionUtils.isEmpty(filters)) {
            return conditions;
        }

        try {
            //TODO: fix first AND
            int filterCount = 0;
            for (QueryFilter filter : filters) {
                String columnName = filter.getField().getColumnName();
                DataType dataType = filter.getField().getDataType();
                List<Object> values;

                if (filter.getValue().getEq() != null) {
                    conditions.add(new Condition(columnName,
                            getValue(dataType, filter.getValue().getEq()), table.getTableName())
                            .withOperatorToBeUsed(Operator.AND)
                            .withConditionToBeUsed(Check.EQUALS));
                } else if (filter.getValue().getNe() != null) {
                    conditions.add(new Condition(columnName,
                            getValue(dataType, filter.getValue().getNe()), table.getTableName())
                            .withOperatorToBeUsed(Operator.AND)
                            .withConditionToBeUsed(Check.NOT_EQUALS));
                } else if (filter.getValue().getLt() != null) {
                    conditions.add(new Condition(columnName,
                            getValue(dataType, filter.getValue().getLt()), table.getTableName())
                            .withOperatorToBeUsed(Operator.AND)
                            .withConditionToBeUsed(Check.LESS_THAN));
                } else if (filter.getValue().getGt() != null) {
                    conditions.add(new Condition(columnName,
                            getValue(dataType, filter.getValue().getGt()), table.getTableName())
                            .withOperatorToBeUsed(Operator.AND)
                            .withConditionToBeUsed(Check.GREATER_THAN));
                } else if (filter.getValue().getLte() != null) {
                    conditions.add(new Condition(columnName,
                            getValue(dataType, filter.getValue().getLte()), table.getTableName())
                            .withOperatorToBeUsed(Operator.AND)
                            .withConditionToBeUsed(Check.LESS_THAN_OR_EQUALS));
                } else if (filter.getValue().getGte() != null) {
                    conditions.add(new Condition(columnName,
                            getValue(dataType, filter.getValue().getGte()), table.getTableName())
                            .withOperatorToBeUsed(Operator.AND)
                            .withConditionToBeUsed(Check.GREATER_THAN_OR_EQUALS));
                } else if (filter.getValue().getIn() != null) {
                    values = filter.getValue().getIn().stream()
                            .map(str -> getValue(filter.getField().getDataType(), str))
                            .collect(Collectors.toList());
                    conditions.add(new Condition(columnName, values, table.getTableName())
                            .withOperatorToBeUsed(Operator.AND).withConditionToBeUsed(Check.IN));
                } else if (filter.getValue().getNin() != null) {
                    values = filter.getValue().getNin().stream()
                            .map(str -> getValue(filter.getField().getDataType(), str))
                            .collect(Collectors.toList());
                    conditions.add(new Condition(columnName, values, table.getTableName())
                            .withOperatorToBeUsed(Operator.AND)
                            .withConditionToBeUsed(Check.NOT_IN));
                } else if (filter.getValue().getBetween() != null) {
                    conditions.add(new Condition(columnName,
                            getValue(dataType, filter.getValue().getBetween().getFrom()),
                            table.getTableName()).withOperatorToBeUsed(Operator.AND)
                            .withConditionToBeUsed(Check.GREATER_THAN_OR_EQUALS));
                    conditions.add(new Condition(columnName,
                            getValue(dataType, filter.getValue().getBetween().getTo()),
                            table.getTableName()).withOperatorToBeUsed(Operator.AND)
                            .withConditionToBeUsed(Check.LESS_THAN));
                }
            }
        } catch (Exception e) {
            logger.error(
                    "An error occurred while building query in GraphQL with inputs: filters: {}, "
                            + "table: {}, condition: {}", filters, table, e);
            throw new GraphQLException("An error occurred while building query in GraphQL", e);
        }

        return conditions;
    }

    private static Object getValue(DataType dataType, String valueString) {
        Object value;
        switch (dataType) {
            case Integer:
                value = Integer.valueOf(valueString);
                break;
            case Long:
                value = Long.valueOf(valueString);
                break;
            case Double:
                value = Double.valueOf(valueString);
                break;
            case LocalDate:
                value = LocalDate.parse(valueString);
                break;
            case Boolean:
                value = Boolean.valueOf(valueString);
                break;
            default:
                value = valueString;
        }
        return value;
    }
}
