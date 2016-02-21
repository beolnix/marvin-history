package com.beolnix.marvin.history.notes;

import com.beolnix.marvin.history.api.model.NoteCreationDTO;
import com.beolnix.marvin.history.api.model.NoteDTO;
import com.beolnix.marvin.history.error.BadRequest;
import com.beolnix.marvin.history.notes.domain.dao.NoteDAO;
import com.beolnix.marvin.history.notes.domain.model.Note;
import com.beolnix.marvin.history.notes.livestreet.PHPClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by beolnix on 21/02/16.
 */

@Service
public class NotesService {

    @Autowired
    private NoteDAO noteDAO;

    @Autowired
    private PHPClient phpClient;

    public NoteDTO createNote(NoteCreationDTO noteCreationDTO) {
        validateNoteCreationDTO(noteCreationDTO);
        Note note = convert(noteCreationDTO);
        Note savedNote = noteDAO.save(note);
        phpClient.sendCreationRequest(savedNote);
        return convert(savedNote);
    }

    private void validateNoteCreationDTO(NoteCreationDTO noteCreationDTO) {
        if (StringUtils.isBlank(noteCreationDTO.getTopic())) {
            throw new BadRequest("Topic must not be empty");
        }

        if (isEmpty(noteCreationDTO.getQuestion())) {
            throw new BadRequest("Question must not be empty");
        }

        if (isEmpty(noteCreationDTO.getAnswer())) {
            throw new BadRequest("Answer must not be empty");
        }
    }

    private boolean isEmpty(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    private Note convert(NoteCreationDTO noteCreationDTO) {
        Note note = new Note();
        BeanUtils.copyProperties(noteCreationDTO, note);
        return note;
    }

    private NoteDTO convert(Note note) {
        NoteDTO noteDTO = new NoteDTO();
        BeanUtils.copyProperties(note, noteDTO);
        return noteDTO;
    }
}
