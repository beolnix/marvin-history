package com.beolnix.marvin.history.chats;

import com.beolnix.marvin.history.api.model.ChatDTO;
import com.beolnix.marvin.history.api.model.CreateChatDTO;
import com.beolnix.marvin.history.error.NotFound;
import com.beolnix.marvin.history.chats.domain.model.Chat;
import com.beolnix.marvin.history.chats.domain.dao.ChatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final static Logger logger = LoggerFactory.getLogger(ChatService.class);

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public ChatDTO createChat(CreateChatDTO createChatDTO) {
        Chat chat = new Chat();
        BeanUtils.copyProperties(createChatDTO, chat);
        Chat savedChat = chatRepository.save(chat);

        return convert(savedChat);
    }

    public ChatDTO getChatById(Long id) {
        Chat chat = chatRepository.findOne(id);
        if (chat != null) {
            return convert(chat);
        } else {
            throw new NotFound();
        }
    }

    public ChatDTO getChatByName(String name) {
        Chat chat = chatRepository.findByName(name).stream()
                .findFirst().orElseThrow(NotFound::new);
        return convert(chat);
    }

    public List<ChatDTO> getChats() {
        return chatRepository.findAll().stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private ChatDTO convert(Chat chat) {
        ChatDTO dto = new ChatDTO();
        BeanUtils.copyProperties(chat, dto);
        return dto;
    }


}
