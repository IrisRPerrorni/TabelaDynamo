package iris.com.dynamodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TabelaModel {
    private String cpf;
    private String nome;
}
