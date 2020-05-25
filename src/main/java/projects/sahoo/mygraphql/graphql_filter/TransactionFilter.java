package projects.sahoo.mygraphql.graphql_filter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransactionFilter implements QueryFilter {

    private TransactionField field;
    private QueryCondition value;

    public TransactionField getField() {
        return field;
    }

    public QueryCondition getValue() {
        return value;
    }
}
