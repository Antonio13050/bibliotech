package com.example.appreactspring.repository;

import com.example.appreactspring.model.OutboxMessage;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;

public interface OutboxMessageRepository extends JpaRepository<OutboxMessage, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<OutboxMessage> findFirst10ByStatusOrderByCreatedAtAsc(OutboxMessage.Status status);
}
