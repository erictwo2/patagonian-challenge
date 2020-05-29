package com.patagonian.challenge.mapper;

import java.util.List;

import com.patagonian.challenge.dto.SongDto;
import com.patagonian.challenge.model.Song;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface SongMapper {

    @Mappings({
        @Mapping(source = "id", target = "songId"),
        @Mapping(source = "name", target = "songTitle")
    })
    @Named("songToSongDto")
    SongDto songToSongDto(Song track);

    @IterableMapping(qualifiedByName = "songToSongDto")
    List<SongDto> songsToSongDtos(List<Song> tracks);

}