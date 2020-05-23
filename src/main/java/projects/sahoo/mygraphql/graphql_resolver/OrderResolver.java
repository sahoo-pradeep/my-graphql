package projects.sahoo.mygraphql.graphql_resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projects.sahoo.mygraphql.model.Order;
import projects.sahoo.mygraphql.model.Transaction;
import projects.sahoo.mygraphql.service.api.TransactionService;

import java.util.List;

@Component
@Slf4j
public class OrderResolver implements GraphQLResolver<Order> {
    private TransactionService transactionService;

    @Autowired
    public OrderResolver(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public List<Transaction> getTransactions(Order order){
        log.info("Get Transactions for order: {}", order);
        return transactionService.getTransactions(order.getOrderId());
    }
}
