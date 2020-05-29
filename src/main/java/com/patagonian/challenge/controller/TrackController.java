package com.patagonian.challenge.controller;

import java.util.ArrayList;
import java.util.List;

import com.patagonian.challenge.model.Track;
import com.patagonian.challenge.service.TrackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tracks")
public class TrackController {

    @Autowired
    private TrackService trackService;

    @GetMapping
    public List<Track> getAllTracks() {
        return trackService.findAll();
    }

}