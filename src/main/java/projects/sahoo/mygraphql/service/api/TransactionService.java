package projects.sahoo.mygraphql.service.api;

import projects.sahoo.mygraphql.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getTransactions(Long orderId);
}
