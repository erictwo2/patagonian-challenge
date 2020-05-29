package com.patagonian.challenge.service;

import java.util.ArrayList;
import java.util.List;

import com.patagonian.challenge.dto.SongDto;
import com.patagonian.challenge.dto.SongsDto;
import com.patagonian.challenge.mapper.SongMapper;
import com.patagonian.challenge.model.Track;
import com.patagonian.challenge.repository.TrackRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackServiceImple implements TrackService {

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private SongMapper songMapper;

    public SongsDto findAll() {
        List<Track> list = trackRepository.findAll();
        SongsDto songsDto = new SongsDto();
        songsDto.setSongs(songMapper.tracksToSongDtos(list));
        return songsDto;
    }

}