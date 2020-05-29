package com.patagonian.challenge.service;

import java.util.List;

import com.patagonian.challenge.model.Track;
import com.patagonian.challenge.repository.TrackRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackServiceImple implements TrackService {

    @Autowired
    private TrackRepository trackRepository;

    public List<Track> findAll() {
        return trackRepository.findAll();
    }

}