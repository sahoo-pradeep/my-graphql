package projects.sahoo.mygraphql.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderSort {
    private OrderField field;
    private SortDirection sortDirection;
}
