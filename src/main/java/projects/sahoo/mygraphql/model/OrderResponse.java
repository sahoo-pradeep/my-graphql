package projects.sahoo.mygraphql.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Order> orders;
    private Long count;
    private List<ErrorMessage> errors;
}
