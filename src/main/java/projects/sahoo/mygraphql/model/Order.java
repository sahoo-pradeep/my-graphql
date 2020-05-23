package projects.sahoo.mygraphql.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long orderId;
    private LocalDateTime orderDate;
    private String orderStatus;
    private Integer totalAmount;
}
