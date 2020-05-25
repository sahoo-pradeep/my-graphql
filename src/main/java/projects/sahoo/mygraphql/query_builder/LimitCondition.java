package projects.sahoo.mygraphql.query_builder;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LimitCondition {
    private int limit;
    private int offset;

    public LimitCondition(int limit) {
        this.limit = limit;
        this.offset = 0;
    }

    public LimitCondition withOffset(int offset){
        this.offset = offset;
        return this;
    }
}
