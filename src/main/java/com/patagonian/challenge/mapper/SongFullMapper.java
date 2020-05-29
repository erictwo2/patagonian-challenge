package com.patagonian.challenge.mapper;

import com.patagonian.challenge.dto.SongFullDto;
import com.patagonian.challenge.model.Song;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SongFullMapper {

    SongFullDto trackToSongDto(Song track);

}