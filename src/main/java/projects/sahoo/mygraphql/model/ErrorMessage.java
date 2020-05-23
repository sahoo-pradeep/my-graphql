package projects.sahoo.mygraphql.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String message;
}
