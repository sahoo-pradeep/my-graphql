package projects.sahoo.mygraphql.graphql_filter;

public enum TransactionField implements QueryField {
    ORDER_ID("order_id", DataType.Long),
    PAYMENT_OPTION("PaymentOption", DataType.String),
    TRANSACTION_STATUS("transactionStatus", DataType.String);

    private String columnName;
    private DataType dataType;

    TransactionField(String columnName, DataType dataType) {
        this.columnName = columnName;
        this.dataType = dataType;
    }

    public String getColumnName() {
        return columnName;
    }

    public DataType getDataType() {
        return dataType;
    }
}
