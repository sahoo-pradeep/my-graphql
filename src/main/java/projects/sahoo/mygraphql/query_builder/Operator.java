package projects.sahoo.mygraphql.query_builder;

public enum Operator {
    AND(" AND "), OR(" OR "), NONE("");

    private String queryString;

    Operator(String queryString) {
        this.queryString = queryString;
    }

    public String getQueryString() {
        return queryString;
    }
}
