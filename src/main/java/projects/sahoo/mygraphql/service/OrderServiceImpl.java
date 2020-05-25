package projects.sahoo.mygraphql.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import projects.sahoo.mygraphql.graphql_filter.OrderFilter;
import projects.sahoo.mygraphql.graphql_filter.OrderSort;
import projects.sahoo.mygraphql.graphql_filter.TransactionFilter;
import projects.sahoo.mygraphql.model.Order;
import projects.sahoo.mygraphql.service.api.OrderService;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public OrderServiceImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

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
