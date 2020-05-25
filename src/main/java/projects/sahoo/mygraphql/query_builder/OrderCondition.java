package projects.sahoo.mygraphql.query_builder;

import lombok.Getter;
import lombok.ToString;
import projects.sahoo.mygraphql.graphql_filter.SortDirection;

@Getter
@ToString
public class OrderCondition {
    private String tableName;
    private String column;
    private SortDirection orderToBeUsed;

    public OrderCondition(String tableName, String column, SortDirection orderToBeUsed) {
        this.tableName = tableName;
        this.column = column;
        this.orderToBeUsed = orderToBeUsed;
    }
}
