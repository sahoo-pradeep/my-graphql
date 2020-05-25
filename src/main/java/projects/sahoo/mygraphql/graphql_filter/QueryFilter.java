package projects.sahoo.mygraphql.graphql_filter;

public interface QueryFilter {
    QueryField getField();
    QueryCondition getValue();
}
