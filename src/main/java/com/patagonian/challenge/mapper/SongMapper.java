package com.patagonian.challenge.mapper;

import com.patagonian.challenge.dto.SongDto;
import com.patagonian.challenge.model.Song;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SongMapper {

    @Mappings({
        @Mapping(source = "externalUrl", target = "externalUrlDto"),
        @Mapping(source = "artists", target = "artistDtos")
    })
    SongDto songToSongDto(Song track);

}