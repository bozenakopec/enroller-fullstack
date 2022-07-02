package com.company.enroller.persistence;

import com.company.enroller.model.Participant;
import org.hibernate.Transaction;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("participantService")
public class ParticipantService {

    private final DatabaseConnector connector;
    private final PasswordEncoder passwordEncoder;

    public ParticipantService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        connector = DatabaseConnector.getInstance();
    }

    public List getAll() {
        return connector.getSession().createCriteria(Participant.class).list();
    }

    public Participant findByLogin(String login) {
        return (Participant) connector.getSession().get(Participant.class, login);
    }

    public Participant add(Participant participant) {
        Transaction transaction = connector.getSession().beginTransaction();
        hashAndChangePassword(participant);
        connector.getSession().save(participant);
        transaction.commit();
        return participant;
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private void hashAndChangePassword(Participant participant) {
        participant.setPassword(encodePassword(participant.getPassword()));
    }

    public void update(Participant participant) {
        Transaction transaction = connector.getSession().beginTransaction();
        connector.getSession().merge(participant);
        transaction.commit();
    }

    public void delete(Participant participant) {
        Transaction transaction = connector.getSession().beginTransaction();
        connector.getSession().delete(participant);
        transaction.commit();
    }

}
