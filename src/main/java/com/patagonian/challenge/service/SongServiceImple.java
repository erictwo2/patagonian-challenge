package com.patagonian.challenge.service;

import java.util.ArrayList;
import java.util.List;

import com.patagonian.challenge.dto.SongDto;
import com.patagonian.challenge.dto.SongsDto;
import com.patagonian.challenge.mapper.SongMapper;
import com.patagonian.challenge.mapper.SimpleSongMapper;
import com.patagonian.challenge.model.Song;
import com.patagonian.challenge.repository.SongRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImple implements SongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SimpleSongMapper simpleSongMapper;

    @Autowired
    private SongMapper songMapper;

    public SongsDto findAllByArtistName(String artistName) {
        List<Song> list = songRepository.findByArtists_Name(artistName);
        SongsDto songsDto = new SongsDto();
        songsDto.setSongs(simpleSongMapper.songsToSimpleSongDtos(list));
        return songsDto;
    }

    public SongDto findById(String id) {
        Song song = songRepository.findById(id).orElse(null);
        return songMapper.songToSongDto(song);
    }

}