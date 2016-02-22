package com.beolnix.marvin.history.notes;

import com.beolnix.marvin.history.api.model.MessageDTO;
import com.beolnix.marvin.history.api.model.NoteCreationDTO;
import com.beolnix.marvin.history.api.model.NoteDTO;
import com.beolnix.marvin.history.error.BadRequest;
import com.beolnix.marvin.history.messages.domain.model.Message;
import com.beolnix.marvin.history.notes.domain.dao.NoteDAO;
import com.beolnix.marvin.history.notes.domain.model.Note;
import com.beolnix.marvin.history.notes.livestreet.client.PHPClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by beolnix on 21/02/16.
 */

@Service
public class NotesService {

    private Logger log = LoggerFactory.getLogger(NotesService.class);

    private final NoteDAO noteDAO;
    private final PHPClient phpClient;

    @Autowired
    public NotesService(NoteDAO noteDAO, PHPClient phpClient) {
        this.noteDAO = noteDAO;
        this.phpClient = phpClient;
    }

    public NoteDTO createNote(NoteCreationDTO noteCreationDTO) {
        validateNoteCreationDTO(noteCreationDTO);
        Note note = convert(noteCreationDTO);
        Note savedNote = noteDAO.save(note);
        publishNote(savedNote);
        return convert(savedNote);
    }

    private void publishNote(Note note) {
        try {
            phpClient.sendCreationRequest(note);
        } catch (Exception e) {
            log.error("Can't post note because of: " + e.getMessage(), e);
        }
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
        note.setQuestion(noteCreationDTO.getQuestion().stream()
                .map(this::convert).collect(Collectors.toList()));
        note.setAnswer(noteCreationDTO.getAnswer().stream()
                .map(this::convert).collect(Collectors.toList()));
        return note;
    }

    private Message convert(MessageDTO messageDTO) {
        Message message = new Message();
        BeanUtils.copyProperties(messageDTO, message);
        return message;
    }

    private MessageDTO convert(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        BeanUtils.copyProperties(message, messageDTO);
        return messageDTO;
    }

    private NoteDTO convert(Note note) {
        NoteDTO noteDTO = new NoteDTO();
        BeanUtils.copyProperties(note, noteDTO);
        noteDTO.setQuestion(note.getQuestion().stream()
                .map(this::convert).collect(Collectors.toList()));
        noteDTO.setAnswer(note.getAnswer().stream()
                .map(this::convert).collect(Collectors.toList()));
        return noteDTO;
    }
}
