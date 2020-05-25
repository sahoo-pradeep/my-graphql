package projects.sahoo.mygraphql.query_builder;

public enum Table {
    Orders("testdb", "ORDERS"),
    Transactions("testdb", "TRANSACTIONS");

    private String database;
    private String tableName;

    Table(String database, String tableName) {
        this.database = database;
        this.tableName = tableName;
    }

    public String getDatabase() {
        return database;
    }

    public String getTableName() {
        return tableName;
    }
}
