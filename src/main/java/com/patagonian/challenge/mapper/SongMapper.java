package com.patagonian.challenge.mapper;

import java.util.List;

import com.patagonian.challenge.dto.SongDto;
import com.patagonian.challenge.model.Song;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SongMapper {

    SongMapper INSTANCE = Mappers.getMapper(SongMapper.class);

    @Mappings({
        @Mapping(source = "id", target = "songId"),
        @Mapping(source = "name", target = "songTitle")
    })
    @Named("trackToSongDto")
    SongDto trackToSongDto(Song track);

    @IterableMapping(qualifiedByName = "trackToSongDto")
    List<SongDto> tracksToSongDtos(List<Song> tracks);

}