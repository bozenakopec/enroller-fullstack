package com.company.enroller.persistence;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("meetingService")
public class MeetingService {

    private final DatabaseConnector connector;

    public MeetingService() {
        connector = DatabaseConnector.getInstance();
    }

    public List getAll() {
        String hql = "FROM Meeting";
        Query query = connector.getSession().createQuery(hql);
        return query.list();
    }


}

