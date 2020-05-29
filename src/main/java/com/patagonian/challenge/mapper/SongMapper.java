package com.patagonian.challenge.mapper;

import com.patagonian.challenge.dto.SongDto;
import com.patagonian.challenge.model.Song;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SongMapper {

    SongDto songToSongDto(Song track);

}