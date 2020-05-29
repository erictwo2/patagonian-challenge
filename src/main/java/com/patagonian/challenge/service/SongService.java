package com.patagonian.challenge.service;

import com.patagonian.challenge.dto.SongFullDto;
import com.patagonian.challenge.dto.SongsDto;

public interface SongService {

    SongsDto findAllByArtistName(String artistName);

    SongFullDto findById(String id);

}