package com.beolnix.marvin.history.service;

import com.beolnix.marvin.history.api.model.CreateMessageDTO;
import com.beolnix.marvin.history.api.model.MessageDTO;
import com.beolnix.marvin.history.error.BadRequest;
import com.beolnix.marvin.history.error.NotFound;
import com.beolnix.marvin.history.model.Message;
import com.beolnix.marvin.history.repository.MessageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by beolnix on 24/01/16.
 */

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final Sort descSortByTimestamp = new Sort(new Sort.Order(Sort.Direction.DESC, "timestamp"));

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public MessageDTO createMessate(CreateMessageDTO createMessageDTO) {
        Message message = new Message();
        BeanUtils.copyProperties(createMessageDTO, message);
        message.setTimestamp(LocalDateTime.now());
        Message savedMessage = messageRepository.save(message);

        return convert(savedMessage);
    }

    public Page<MessageDTO> getMessages(Long chatId,
                                        Long toMessageId,
                                        LocalDateTime fromDateTime,
                                        LocalDateTime toDateTime,
                                        Pageable pageable) {

        Pageable preparedSort = prepareDESCSort(pageable);
        if (toMessageId == null && fromDateTime == null && toDateTime == null) {
            return getNewest(chatId, preparedSort);
        } else if (toMessageId != null) {
            return getForScropUp(chatId, toMessageId, preparedSort);
        } else if (fromDateTime != null && toDateTime != null) {
            return getForJumpInTime(chatId, fromDateTime, toDateTime, preparedSort);
        }

        throw new BadRequest("Incorrect combination of filtration params.");

    }

    private Page<MessageDTO> getForJumpInTime(Long chatId, LocalDateTime fromDateTime, LocalDateTime toDateTime, Pageable pageable) {
        Page<Message> page = messageRepository.findByChatIdAndTimestampLessThanAndTimestampGreaterThan(chatId,
                toDateTime,
                fromDateTime,
                pageable);
        return convert(page, pageable);
    }

    private Page<MessageDTO> getForScropUp(Long chatId, Long toMessageId, Pageable pageable) {
        Page<Message> page = messageRepository.findByChatIdAndIdLessThan(chatId, toMessageId, pageable);
        return convert(page, pageable);
    }

    private Pageable prepareDESCSort(Pageable pageable) {
        return new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), MessageRepository.descSortByTimestamp);
    }

    private Page<MessageDTO> convert(Page<Message> page, Pageable pageable) {
        List<MessageDTO> entities = page.getContent().stream()
                .map(this::convert)
                .collect(Collectors.toList());

        return new PageImpl<MessageDTO>(entities, pageable, page.getTotalPages());
    }

    private Page<MessageDTO> getNewest(Long chatId, Pageable pageable) {
        Page<Message> page = messageRepository.findByChatId(chatId, pageable);
        return convert(page, pageable);
    }

    private MessageDTO convert(Message message) {
        MessageDTO dto = new MessageDTO();
        BeanUtils.copyProperties(message, dto);
        return dto;
    }
}
