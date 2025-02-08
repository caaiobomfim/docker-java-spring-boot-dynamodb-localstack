package caaiobomfim.docker_java_spring_boot_dynamodb_localstack.repository;

import caaiobomfim.docker_java_spring_boot_dynamodb_localstack.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Repository
public class MessageRepository {

    private static final Logger logger = LoggerFactory.getLogger(MessageRepository.class);
    private final DynamoDbTable<Message> table;

    public MessageRepository(DynamoDbClient dynamoDbClient) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();

        this.table = enhancedClient.table("Messages", TableSchema.fromBean(Message.class));
    }

    public void save(Message message) {
        logger.info("Saving message to DynamoDB: {}", message);
        table.putItem(message);
        logger.info("Message saved successfully.");
    }

    public Message findById(String id) {
        logger.info("Searching for message with ID: {}", id);
        Message message = table.getItem(r -> r.key(k -> k.partitionValue(id)));

        if (message != null) {
            logger.info("Message found: {}", message);
        } else {
            logger.warn("Message with ID '{}' not found in DynamoDB.", id);
        }
        return message;
    }
}
