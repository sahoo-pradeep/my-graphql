package projects.sahoo.mygraphql.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import projects.sahoo.mygraphql.model.Transaction;
import projects.sahoo.mygraphql.service.api.TransactionService;

import java.util.List;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    @Override
    public List<Transaction> getTransactions(Long orderId) {
        return null;
    }
}
