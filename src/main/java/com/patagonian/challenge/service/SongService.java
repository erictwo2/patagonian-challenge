package com.patagonian.challenge.service;

import com.patagonian.challenge.dto.SongDto;
import com.patagonian.challenge.dto.SongsDto;

public interface SongService {

    SongsDto findAllByArtistName(String artistName);

    SongDto findById(String id);

}