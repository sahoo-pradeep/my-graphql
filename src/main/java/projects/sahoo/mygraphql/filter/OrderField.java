package projects.sahoo.mygraphql.filter;

public enum OrderField implements QueryField {
    ORDER_ID("orderId", DataType.Long),
    ORDER_DATE("orderDate", DataType.LocalDate),
    ORDER_STATUS("orderStatus", DataType.String);

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
