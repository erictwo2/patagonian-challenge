package com.patagonian.challenge.service;

import com.patagonian.challenge.dto.SimpleSongDto;
import com.patagonian.challenge.dto.SongDto;
import com.patagonian.challenge.dto.SongsDto;
import org.springframework.data.domain.Slice;

public interface SongService {
  SongsDto findAllByArtistName(String artistName);

  Slice<SimpleSongDto> findAllByArtistName(String artistName, Integer page, Integer size, String sort);

  SongDto findById(String id);
}
