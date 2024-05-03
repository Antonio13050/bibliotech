package com.example.appreactspring.service.outboxmessage;

import com.example.appreactspring.core.JsonConverter;
import com.example.appreactspring.core.MessageSender;
import com.example.appreactspring.model.OutboxMessage;
import com.example.appreactspring.repository.OutboxMessageRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutboxMessageService {

    private static final Logger log = LoggerFactory.getLogger(OutboxMessageService.class);
    private final OutboxMessageRepository outboxMessageRepository;
    private final JsonConverter jsonConverter;
    private final MessageSender messageSender;

    public OutboxMessageService(OutboxMessageRepository outboxMessageRepository, JsonConverter jsonConverter, MessageSender messageSender) {
        this.outboxMessageRepository = outboxMessageRepository;
        this.jsonConverter = jsonConverter;
        this.messageSender = messageSender;
    }

    @Transactional
    public void store(String destination, Object content) {
        var json = jsonConverter.toJson(content);
        var outbox = new OutboxMessage(destination, json);
        outboxMessageRepository.save(outbox);
    }

    @Transactional
    public void sendTopPendingMessages(){
        List<OutboxMessage> outboxMessages = outboxMessageRepository
                .findFirst10ByStatusOrderByCreatedAtAsc(OutboxMessage.Status.PENDING);

        for (OutboxMessage outboxMessage: outboxMessages) {
            outboxMessage.increaseTentatives();
            try{
                messageSender.send(outboxMessage.getDestination(), outboxMessage.getContent());
            }
            catch (Exception e){
                log.error("Erro ao enviar mensagem para o message broker", e);
                if (outboxMessage.getTentatives() >= 2) {
                    outboxMessage.setStatus(OutboxMessage.Status.ERROR);
                    outboxMessageRepository.save(outboxMessage);
                }
                continue;
            }
            outboxMessage.setStatus(OutboxMessage.Status.SENT);
            outboxMessageRepository.save(outboxMessage);
        }

    }
}
