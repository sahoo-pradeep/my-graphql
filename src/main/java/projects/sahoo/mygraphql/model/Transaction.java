package projects.sahoo.mygraphql.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long transactionId;
    private Long orderId;
    private String paymentOption;
    private Integer amount;
    private LocalDateTime transactionDate;
    private String transactionStatus;
}
