package com.beolnix.marvin.history.repository;


import com.beolnix.marvin.history.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by beolnix on 18/01/16.
 */
public interface ChatRepository extends JpaRepository<Chat, Long> {

    public List<Chat> findByName(String name);
}
