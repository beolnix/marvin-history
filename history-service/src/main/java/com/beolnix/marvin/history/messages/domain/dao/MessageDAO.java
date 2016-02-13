package com.beolnix.marvin.history.messages.domain.dao;

import com.beolnix.marvin.history.messages.domain.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;


public interface MessageDAO extends PagingAndSortingRepository<Message, String> {

    Sort descSortByTimestamp = new Sort(new Sort.Order(Sort.Direction.DESC, "timestamp"));

    Page<Message> findByChatId(String chatId, Pageable pageable);

    Page<Message> findByChatIdAndTimestampLessThanEqual(String chatId, LocalDateTime timestamp, Pageable pageable);

    Message findOneByChatIdAndId(String chatId, String id);

    Page<Message> findByChatIdAndTimestampLessThanAndTimestampGreaterThan(String chatId,
                                                                          LocalDateTime toDateTime,
                                                                          LocalDateTime fromDateTime,
                                                                          Pageable pageable);

}
