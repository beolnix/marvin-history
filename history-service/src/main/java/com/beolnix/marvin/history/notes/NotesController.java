package com.beolnix.marvin.history.notes;

import com.beolnix.marvin.history.api.NotesApi;
import com.beolnix.marvin.history.api.model.NoteCreationDTO;
import com.beolnix.marvin.history.api.model.NoteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by beolnix on 21/02/16.
 */
@RestController
public class NotesController implements NotesApi {

    private final NotesService notesService;

    @Autowired
    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @Override
    public NoteDTO createNote(@RequestBody NoteCreationDTO noteCreationDTO) {
        return notesService.createNote(noteCreationDTO);
    }
}
