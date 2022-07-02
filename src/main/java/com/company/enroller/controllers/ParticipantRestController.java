package com.company.enroller.controllers;

import com.company.enroller.model.Participant;
import com.company.enroller.persistence.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/participants")
@RequiredArgsConstructor
public class ParticipantRestController {
    private final ParticipantService participantService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        Collection<Participant> participants = participantService.getAll();
        return new ResponseEntity<>(participants, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable("id") String login) {
        Participant participant = participantService.findByLogin(login);
        if (participant == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(participant, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> addParticipant(@RequestBody Participant participant) {
        if (participantService.findByLogin(participant.getLogin()) != null) {
            return new ResponseEntity(
                    "Unable to create. A participant with login " + participant.getLogin() + " already exist.",
                    HttpStatus.CONFLICT);
        }
        participantService.add(participant);
        return new ResponseEntity<>(participant, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") String login) {
        Participant participant = participantService.findByLogin(login);
        if (participant == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        participantService.delete(participant);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("id") String login,
                                    @RequestBody Participant updatedParticipant) {
        if (participantService.findByLogin(login) != null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        updatedParticipant.setLogin(login);
        participantService.update(updatedParticipant);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
