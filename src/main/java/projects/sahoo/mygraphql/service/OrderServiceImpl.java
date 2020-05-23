package projects.sahoo.mygraphql.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import projects.sahoo.mygraphql.filter.OrderFilter;
import projects.sahoo.mygraphql.filter.OrderSort;
import projects.sahoo.mygraphql.filter.TransactionFilter;
import projects.sahoo.mygraphql.model.Order;
import projects.sahoo.mygraphql.service.api.OrderService;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Override
    public Long getOrderCount(List<OrderFilter> orderFilters,
            List<TransactionFilter> transactionFilters) {
        return 0L;
    }

    @Override
    public List<Order> getOrders(List<OrderFilter> orderFilters,
            List<TransactionFilter> transactionFilters, List<OrderSort> sortList, int limit,
            int offset) {
        return null;
    }
}
