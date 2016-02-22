package com.beolnix.marvin;

import com.beolnix.marvin.history.api.model.MessageDTO;
import com.beolnix.marvin.history.api.model.NoteCreationDTO;
import com.beolnix.marvin.history.messages.domain.model.Message;
import com.beolnix.marvin.history.notes.NotesService;
import com.beolnix.marvin.history.notes.domain.dao.NoteDAO;
import com.beolnix.marvin.history.notes.domain.model.Note;
import com.beolnix.marvin.history.notes.livestreet.client.LifestreetAuthenticator;
import com.beolnix.marvin.history.notes.livestreet.client.PHPClient;
import com.beolnix.marvin.history.notes.livestreet.util.AuthExtractor;
import com.google.common.collect.Lists;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by beolnix on 21/02/16.
 */
public class PHPTest {

    private final static String login = "beolnix@gmail.com";
    private final static String password = "---";
    private final static String host = "asdcode-dev.buildloft.com";

    @Test
    @Ignore
    public void testPHPClient() throws Exception {
        LifestreetAuthenticator authenticator = getAuthenticator();
        PHPClient phpClient = getPhpClient(authenticator);
        phpClient.sendCreationRequest(createNote());
    }

    @Test
    @Ignore
    public void testService() throws Exception {
        LifestreetAuthenticator authenticator = getAuthenticator();
        PHPClient phpClient = getPhpClient(authenticator);
        NotesService service = new NotesService(noteDAO(), phpClient);
        service.createNote(noteCreationDTO());
    }

    public NoteCreationDTO noteCreationDTO() {
        NoteCreationDTO noteCreationDTO = new NoteCreationDTO();
        noteCreationDTO.setTopic("bala. bala baleyla!");
        noteCreationDTO.setQuestion(Lists.newArrayList(msgDTO("raz"), msgDTO("dva")));
        noteCreationDTO.setAnswer(Lists.newArrayList(msgDTO("tri"), msgDTO("cheture")));
        return noteCreationDTO;
    }

    public NoteDAO noteDAO() {
        return new NoteDAO() {
            @Override
            public Iterable<Note> findAll(Sort sort) {
                return null;
            }

            @Override
            public Page<Note> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public Note save(Note entity) {
                entity.setId(UUID.randomUUID().toString());
                return entity;
            }

            @Override
            public <S extends Note> Iterable<S> save(Iterable<S> entities) {
                return null;
            }

            @Override
            public Note findOne(String s) {
                return null;
            }

            @Override
            public boolean exists(String s) {
                return false;
            }

            @Override
            public Iterable<Note> findAll() {
                return null;
            }

            @Override
            public Iterable<Note> findAll(Iterable<String> strings) {
                return null;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void delete(String s) {

            }

            @Override
            public void delete(Note entity) {

            }

            @Override
            public void delete(Iterable<? extends Note> entities) {

            }

            @Override
            public void deleteAll() {

            }
        };
    }

    public Note createNote() {
        Note note = new Note();
        note.setTopic("abra kadabra");
        note.setQuestion(Lists.newArrayList(msg("first"), msg("second")));
        note.setAnswer(Lists.newArrayList(msg("third"), msg("fourth")));
        note.setId(UUID.randomUUID().toString());
        return note;
    }

    public Message msg(String text) {
        Message msg = new Message();
        msg.setAuthor("beolnix");
        msg.setTimestamp(LocalDateTime.now());
        msg.setId(UUID.randomUUID().toString());
        msg.setMsg(text);
        return msg;
    }

    public MessageDTO msgDTO(String text) {
        MessageDTO msg = new MessageDTO();
        msg.setAuthor("beolnix");
        msg.setTimestamp(LocalDateTime.now());
        msg.setId(UUID.randomUUID().toString());
        msg.setMsg(text);
        return msg;
    }

    public PHPClient getPhpClient(LifestreetAuthenticator authenticator) {
        PHPClient phpClient = new PHPClient(authenticator);
        phpClient.setPhphost(host);
        phpClient.setBlogId("2");
        phpClient.setPostAttemptsLimit(3);
        return phpClient;
    }

    public LifestreetAuthenticator getAuthenticator() {
        LifestreetAuthenticator authenticator = new LifestreetAuthenticator(new AuthExtractor());
        authenticator.setLogin(login);
        authenticator.setPassword(password);
        authenticator.setPhphost(host);
        return authenticator;
    }


}
