package iris.com.dynamodb;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.Map;

@RestController
@RequestMapping("/api/dynamodb")
public class ControllerDynamo {
    private final String tabela = "Cadastro";

    private final DynamoDbClient dynamoDbClient;

    public ControllerDynamo(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }


    @PostConstruct
    public String criarTabela() {
        CreateTableRequest createTableRequest = CreateTableRequest.builder()
                .attributeDefinitions(
                        AttributeDefinition.builder()
                                .attributeName("cpf")
                                .attributeType(ScalarAttributeType.S)
                                .build(),
                        AttributeDefinition.builder()
                                .attributeName("nome")
                                .attributeType(ScalarAttributeType.S)
                                .build())
                .keySchema(
                        KeySchemaElement.builder()
                                .attributeName("cpf")
                                .keyType(KeyType.HASH)
                                .build(),
                        KeySchemaElement.builder()
                                .attributeName("nome")
                                .keyType(KeyType.RANGE)
                                .build())
                .provisionedThroughput(
                        ProvisionedThroughput.builder()
                                .readCapacityUnits(10L)
                                .writeCapacityUnits(10L).build())
                .tableName(tabela)
                .build();

        String tableId = "";

        try {
            CreateTableResponse result = dynamoDbClient.createTable(createTableRequest);
            tableId = result.tableDescription().tableId();
            return tableId;
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return "";
    }


    @PostMapping("/inserir")
    public String inserirDados(@RequestBody TabelaModel tabelaModel) {
        PutItemRequest putItemRequest = PutItemRequest.builder()
                .tableName(tabela)
                .item(Map.of("cpf", AttributeValue.builder().s(tabelaModel.getCpf()).build(),
                        "nome", AttributeValue.builder().s(tabelaModel.getNome()).build())).build();
        dynamoDbClient.putItem(putItemRequest);
        return "dados inseridos na tabela: " + tabela;
    }

    @GetMapping("/buscar")
    public String busca(@RequestParam String cpf, @RequestParam String nome) {
        GetItemRequest getItemRequest = GetItemRequest.builder().
                tableName(tabela).
                key(Map.of("cpf", AttributeValue.builder().s(cpf).build(),
                        "nome", AttributeValue.builder().s(nome).build()))
                .build();

        GetItemResponse getItemResponse = dynamoDbClient.getItem(getItemRequest);
        if (getItemResponse.hasItem()) {
            return "Cpf: " + cpf + " / Nome:  " + nome;
        } else {
            return "Não foi encontrado esse resultado de pesquisa";
        }
    }
}
