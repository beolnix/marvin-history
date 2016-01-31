package com.beolnix.marvin.history.messages.domain.dao;

import com.beolnix.marvin.history.messages.domain.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;


public interface MessageDAO extends JpaRepository<Message, Long> {

    Sort descSortByTimestamp = new Sort(new Sort.Order(Sort.Direction.DESC, "timestamp"));

    Page<Message> findByChatId(Long chatId, Pageable pageable);

    Page<Message> findByChatIdAndIdLessThan(Long chatId, Long id, Pageable pageable);

    Page<Message> findByChatIdAndTimestampLessThanAndTimestampGreaterThan(Long chatId,
                                                                          LocalDateTime toDateTime,
                                                                          LocalDateTime fromDateTime,
                                                                          Pageable pageable);

}
