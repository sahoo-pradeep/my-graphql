package projects.sahoo.mygraphql.graphql_query_resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projects.sahoo.mygraphql.filter.OrderFilter;
import projects.sahoo.mygraphql.filter.OrderSort;
import projects.sahoo.mygraphql.filter.TransactionFilter;
import projects.sahoo.mygraphql.model.ErrorMessage;
import projects.sahoo.mygraphql.model.Order;
import projects.sahoo.mygraphql.model.OrderResponse;
import projects.sahoo.mygraphql.service.api.OrderService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class OrderQueryResolver implements GraphQLQueryResolver {
    private static final String COUNT_FIELD = "count";
    private static final List<ErrorMessage> ERROR_LIST = new ArrayList<>();

    private OrderService orderService;

    @Autowired
    public OrderQueryResolver(OrderService orderService) {
        this.orderService = orderService;
    }

    public OrderResponse getOrders(List<OrderFilter> orderFilters,
            List<TransactionFilter> transactionFilters, List<OrderSort> sortList, int limit,
            int offset, DataFetchingEnvironment environment) {
        log.info("Failed to get response for input: orderFilters = {}, transactionFilters = "
                        + "{}, sortList = {}, limit = {}, offset = {}", orderFilters,
                transactionFilters,
                sortList, limit, offset);

        try {
            Long count = environment.getSelectionSet().get().containsKey(COUNT_FIELD) ?
                    orderService.getOrderCount(orderFilters, transactionFilters) :
                    0L;
            List<Order> orders = orderService
                    .getOrders(orderFilters, transactionFilters, sortList, limit, offset);

            return OrderResponse.builder().count(count).orders(orders).errors(ERROR_LIST).build();
        } catch (Exception e) {
            log.error("Failed to get response for input: orderFilters = {}, transactionFilters = "
                            + "{}, sortList = {}, limit = {}, offset = {}", orderFilters,
                    transactionFilters, sortList, limit, offset, e);

            List<ErrorMessage> errors = Arrays.asList(new ErrorMessage(e.getMessage()));
            return OrderResponse.builder().count(0L).errors(errors).build();
        }
    }
}
