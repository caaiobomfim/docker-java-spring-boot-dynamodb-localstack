package caaiobomfim.docker_java_spring_boot_dynamodb_localstack.controller;

import caaiobomfim.docker_java_spring_boot_dynamodb_localstack.model.Message;
import caaiobomfim.docker_java_spring_boot_dynamodb_localstack.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
    private final MessageRepository repository;

    public MessageController(MessageRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Message createMessage(@RequestBody Message message) {
        logger.info("Received POST request: {}", message);
        message.setId(UUID.randomUUID().toString());
        logger.info("Generated ID: {}", message.getId());
        repository.save(message);
        logger.info("Message successfully saved to DynamoDB: {}", message);
        return message;
    }

    @GetMapping("/{id}")
    public Message findMessage(@PathVariable String id) {
        logger.info("Received GET request for ID: {}", id);
        Message foundMessage = repository.findById(id);
        if (foundMessage != null) {
            logger.info("Message found: {}", foundMessage);
        } else {
            logger.warn("⚠Message with ID '{}' not found in DynamoDB.", id);
        }
        return foundMessage;
    }
}
