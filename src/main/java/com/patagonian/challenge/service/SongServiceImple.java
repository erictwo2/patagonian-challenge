package com.patagonian.challenge.service;

import com.patagonian.challenge.dto.SimpleSongDto;
import com.patagonian.challenge.dto.SongDto;
import com.patagonian.challenge.dto.SongsDto;
import com.patagonian.challenge.exception.NotFoundException;
import com.patagonian.challenge.mapper.SimpleSongMapper;
import com.patagonian.challenge.mapper.SongMapper;
import com.patagonian.challenge.model.Song;
import com.patagonian.challenge.repository.SongRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
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
    List<Song> list = songRepository.findByArtists_NameIgnoreCaseOrderByNameAsc(artistName);
    SongsDto songsDto = new SongsDto();
    songsDto.setSongs(simpleSongMapper.songsToSimpleSongDtos(list));
    return songsDto;
  }

  public Slice<SimpleSongDto> findAllByArtistName(String artistName, Integer page, Integer size, String sort) {
    Sort nameSort = sort.toLowerCase() == "desc" ? Sort.by("name").descending() : Sort.by("name").ascending();
    Slice<Song> songsPage = songRepository.findByArtists_NameIgnoreCase(
      artistName,
      PageRequest.of(page, size, nameSort)
    );
    Slice<SimpleSongDto> songsPageDto = songsPage.map(song -> simpleSongMapper.songToSimpleSongDto(song));
    return songsPageDto;
  }

  public SongDto findById(String id) {
    Song song = songRepository.findById(id).orElseThrow(() -> new NotFoundException("Song not found"));
    return songMapper.songToSongDto(song);
  }
}
