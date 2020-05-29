package com.patagonian.challenge.service;

import com.patagonian.challenge.dto.SongsDto;

public interface TrackService {

    SongsDto findAllByArtistName(String artistName);

}