package com.patagonian.challenge.service;

import java.util.ArrayList;
import java.util.List;

import com.patagonian.challenge.dto.SongsDto;
import com.patagonian.challenge.mapper.SongMapper;
import com.patagonian.challenge.model.Song;
import com.patagonian.challenge.repository.SongRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImple implements SongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SongMapper songMapper;

    public SongsDto findAllByArtistName(String artistName) {
        List<Song> list = songRepository.findByArtists_Name(artistName);
        SongsDto songsDto = new SongsDto();
        songsDto.setSongs(songMapper.tracksToSongDtos(list));
        return songsDto;
    }

}