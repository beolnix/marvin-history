package com.beolnix.marvin.history.repository;

import com.beolnix.marvin.history.model.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by beolnix on 18/01/16.
 */
public interface MessageRepository extends Repository<Message, Long> {
    List<Message> findByChatId(Long chatId, Pageable pageable);
}
