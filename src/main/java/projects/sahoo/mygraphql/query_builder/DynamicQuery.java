package projects.sahoo.mygraphql.query_builder;

import org.apache.logging.log4j.util.Strings;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class DynamicQuery {

    public static QueryBuilder create() {
        return new QueryBuilder();
    }

    public static final class QueryBuilder {

        private String selectString = Strings.EMPTY;
        private String countString = Strings.EMPTY;
        private String joinString = Strings.EMPTY;
        private String whereString = Strings.EMPTY;
        private String orderByString = Strings.EMPTY;
        private String limitString = Strings.EMPTY;

        public QueryBuilder select(Table primaryTable) {
            this.selectString = "SELECT " + primaryTable.getTableName() + ".* FROM " + getFullTableName(primaryTable);
            return this;
        }

        public QueryBuilder count(Table primaryTable) {
            this.countString = "SELECT COUNT(1) FROM " + getFullTableName(primaryTable);
            return this;
        }

        public QueryBuilder join(Table primaryTable, Table joinTable, List<JoinCondition> joinConditions) {
            // to support multiple join clause
            this.joinString += generateJoinClause(joinConditions, primaryTable, joinTable);
            return this;
        }

        public QueryBuilder where(List<Condition> conditions) {
            this.whereString = generateWhereClause(conditions);
            return this;
        }

        public QueryBuilder orderBy(List<OrderCondition> orderConditions) {
            this.orderByString = generateOrderByClause(orderConditions);
            return this;
        }

        public QueryBuilder limit(LimitCondition limitCondition) {
            this.limitString = generateLimitClause(limitCondition);
            return this;
        }

        public String build() {
            StringBuilder query = new StringBuilder();

            if (!selectString.equals(Strings.EMPTY)) {
                query.append(selectString);
            } else if (!countString.equals(Strings.EMPTY)) {
                query.append(countString);
            }

            query.append(joinString).append(whereString).append(orderByString).append(limitString);

            return query.toString();
        }
    }

    private static String generateJoinClause(List<JoinCondition> joinConditions, Table primaryTable,
            Table joinTable) {
        if (CollectionUtils.isEmpty(joinConditions)) {
            return Strings.EMPTY;
        }

        StringBuilder joinString =
                new StringBuilder(" JOIN " + getFullTableName(joinTable) + " ON (");
        joinConditions.forEach(condition -> {
            joinString.append(condition.getOperatorToBeUsed().getQueryString());

            joinString.append(primaryTable.getTableName()).append(".")
                    .append(condition.getPrimaryTableColumnName()).append(" = ")
                    .append(joinTable.getTableName()).append(".")
                    .append(condition.getJoinTableColumnName());

        });

        joinString.append(")");

        return joinString.toString();
    }

    private static String generateWhereClause(List<Condition> conditions) {
        if (CollectionUtils.isEmpty(conditions)) {
            return Strings.EMPTY;
        }

        StringBuilder whereString = new StringBuilder(" WHERE ");
        conditions.forEach(condition -> {
            whereString.append(condition.getOperatorToBeUsed().getQueryString());
            whereString.append(condition.getTableName()).append(".");
            whereString.append(condition.getColumnName());

            switch (condition.getConditionToBeUsed()) {
                case EQUALS:
                    whereString.append(condition.getValue().equals("NULL") ?
                            " IS NULL" :
                            " = " + condition.getValue());
                    break;

                case NOT_EQUALS:
                    whereString.append(condition.getValue().equals("NULL") ?
                            " IS NOT NULL" :
                            " != " + condition.getValue());
                    break;
                case GREATER_THAN:
                    whereString.append(" > ").append(condition.getValue());
                    break;
                case GREATER_THAN_OR_EQUALS:
                    whereString.append(" >= ").append(condition.getValue());
                    break;
                case LESS_THAN:
                    whereString.append(" < ").append(condition.getValue());
                    break;
                case LESS_THAN_OR_EQUALS:
                    whereString.append(" <= ").append(condition.getValue());
                    break;
                case LIKE:
                    whereString.append(" LIKE ").append(condition.getValue());
                    break;
                case IN:
                    whereString.append(" IN (").append(condition.getValue()).append(")");
                    break;
                case NOT_IN:
                    whereString.append(" NOT IN (").append(condition.getValue()).append(")");
                    break;
            }
        });

        return whereString.toString();
    }

    private static String generateOrderByClause(List<OrderCondition> orderConditions) {
        if (CollectionUtils.isEmpty(orderConditions)) {
            return Strings.EMPTY;
        }

        StringBuilder orderString = new StringBuilder(" ORDER BY ");
        boolean firstValue = true;
        for (OrderCondition condition : orderConditions) {
            if (firstValue) {
                firstValue = false;
            } else {
                orderString.append(", ");
            }

            orderString.append(condition.getTableName()).append(".").append(condition.getColumn()).append(" ").append(
                condition.getOrderToBeUsed().name());
        }

        return orderString.toString();
    }

    private static String generateLimitClause(LimitCondition limitCondition) {
        return " LIMIT " + limitCondition.getLimit() + " OFFSET " + limitCondition.getOffset();
    }

    private static String getFullTableName(Table table) {
        return table.getDatabase() + "." + table.getTableName();
    }
}
