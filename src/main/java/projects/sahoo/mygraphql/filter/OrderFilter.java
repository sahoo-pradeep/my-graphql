package projects.sahoo.mygraphql.filter;

import lombok.ToString;

@ToString
public class OrderFilter implements QueryFilter {
    private OrderField field;
    private QueryCondition value;

    public OrderField getField() {
        return field;
    }

    public QueryCondition getValue() {
        return value;
    }
}
