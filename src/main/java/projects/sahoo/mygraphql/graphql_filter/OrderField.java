package projects.sahoo.mygraphql.graphql_filter;

public enum OrderField implements QueryField {
    ORDER_ID("order_id", DataType.Long),
    ORDER_DATE("order_date", DataType.LocalDate),
    ORDER_STATUS("order_status", DataType.String);

    OrderField(String columnName, DataType dataType) {
        this.columnName = columnName;
        this.dataType = dataType;
    }

    private String columnName;
    private DataType dataType;

    public String getColumnName() {
        return columnName;
    }

    public DataType getDataType() {
        return dataType;
    }
}
