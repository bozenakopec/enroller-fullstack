package com.company.enroller.controllers;

import com.company.enroller.persistence.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/meetings")
@RequiredArgsConstructor
public class MeetingRestController {
    private final MeetingService meetingService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getMeetings() {
        List meetings = meetingService.getAll();
        return new ResponseEntity<>(meetings, HttpStatus.OK);
    }
}
