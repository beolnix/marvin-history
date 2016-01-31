package com.beolnix.marvin.history.chats.domain.dao;


import com.beolnix.marvin.history.chats.domain.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by beolnix on 18/01/16.
 */
public interface ChatRepository extends JpaRepository<Chat, Long> {

    public List<Chat> findByName(String name);
}
