package projects.sahoo.mygraphql.query_builder;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class JoinCondition {
    private String primaryTableColumnName;
    private String joinTableColumnName;
    private Operator operatorToBeUsed;

    public JoinCondition(String primaryTableColumnName, String joinTableColumnName) {
        this.primaryTableColumnName = primaryTableColumnName;
        this.joinTableColumnName = joinTableColumnName;
        this.operatorToBeUsed = Operator.NONE;
    }

    public JoinCondition withOperatorToBeUsed(Operator operatorToBeUsed){
        this.operatorToBeUsed = operatorToBeUsed;
        return this;
    }
}
