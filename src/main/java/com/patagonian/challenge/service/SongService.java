package com.patagonian.challenge.service;

import com.patagonian.challenge.dto.SongsDto;

public interface SongService {

    SongsDto findAllByArtistName(String artistName);

}