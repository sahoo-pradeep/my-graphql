package projects.sahoo.mygraphql.service;

import graphql.GraphQLException;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import projects.sahoo.mygraphql.graphql_filter.OrderField;
import projects.sahoo.mygraphql.graphql_filter.OrderFilter;
import projects.sahoo.mygraphql.graphql_filter.OrderSort;
import projects.sahoo.mygraphql.graphql_filter.TransactionField;
import projects.sahoo.mygraphql.graphql_filter.TransactionFilter;
import projects.sahoo.mygraphql.model.Order;
import projects.sahoo.mygraphql.query_builder.Condition;
import projects.sahoo.mygraphql.query_builder.DynamicQuery;
import projects.sahoo.mygraphql.query_builder.GraphQLQueryUtils;
import projects.sahoo.mygraphql.query_builder.JoinCondition;
import projects.sahoo.mygraphql.query_builder.Table;
import projects.sahoo.mygraphql.service.api.OrderService;

import java.util.HashMap;
import java.util.LinkedList;
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
        String queryString = Strings.EMPTY;
        try {
            List<JoinCondition> paymentJoinConditions = new LinkedList<>();
            List<Condition> conditions = new LinkedList<>();

            // JOIN
            if (!CollectionUtils.isEmpty(transactionFilters)) {
                paymentJoinConditions.add(new JoinCondition(OrderField.ORDER_ID.getColumnName(),
                        TransactionField.ORDER_ID.getColumnName()));
            }

            // WHERE
            conditions.addAll(GraphQLQueryUtils.getConditions(orderFilters, Table.Orders));
            conditions.addAll(GraphQLQueryUtils.getConditions(transactionFilters, Table.Transactions));

            // BUILD
            queryString = DynamicQuery.create().count(Table.Orders)
                    .join(Table.Orders, Table.Transactions,
                            paymentJoinConditions).where(conditions).build();
            log.debug("GraphQL Query Generated: {}", queryString);

            // EXECUTE
            return namedParameterJdbcTemplate
                    .queryForObject(queryString, new HashMap<>(), Long.class);
        } catch (GraphQLException e1) {
            throw e1;
        } catch (Exception e2) {
            log.error("Exception in executing DB query. Query: {}", queryString, e2);
            throw e2;
        }
    }

    @Override
    public List<Order> getOrders(List<OrderFilter> orderFilters,
            List<TransactionFilter> transactionFilters, List<OrderSort> sortList, int limit,
            int offset) {
        return null;
    }
}
