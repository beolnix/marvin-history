package com.beolnix.marvin.history.notes.domain.dao;

import com.beolnix.marvin.history.notes.domain.model.Note;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by beolnix on 21/02/16.
 */
public interface NoteDAO extends PagingAndSortingRepository<Note, String> {

}
