package projects.sahoo.mygraphql.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Between {
    private String from;
    private String to;
}
