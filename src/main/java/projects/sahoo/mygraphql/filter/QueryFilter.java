package projects.sahoo.mygraphql.filter;

public interface QueryFilter {
    QueryField getField();
    QueryCondition getValue();
}
