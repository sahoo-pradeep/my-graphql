package projects.sahoo.mygraphql.filter;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QueryCondition {

    private String eq;
    private String ne;
    private String lt;
    private String gt;
    private String lte;
    private String gte;
    private List<String> in;
    private List<String> nin;
    private Between between;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QueryCondition{");

        if (eq != null) {
            sb.append("eq='").append(eq).append('\'');
        } else if (ne != null) {
            sb.append("ne='").append(ne).append('\'');
        } else if (lt != null) {
            sb.append("lt='").append(lt).append('\'');
        } else if (gt != null) {
            sb.append("gt='").append(gt).append('\'');
        } else if (lte != null) {
            sb.append("lte='").append(lte).append('\'');
        } else if (gte != null) {
            sb.append("gte='").append(gte).append('\'');
        } else if (in != null) {
            sb.append("in='").append(in).append('\'');
        } else if (nin != null) {
            sb.append("nin='").append(nin).append('\'');
        } else if (between != null) {
            sb.append("between='").append(between).append('\'');
        }

        sb.append('}');
        return sb.toString();
    }
}
