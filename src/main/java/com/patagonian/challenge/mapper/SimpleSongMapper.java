package com.patagonian.challenge.mapper;

import java.util.List;

import com.patagonian.challenge.dto.SimpleSongDto;
import com.patagonian.challenge.model.Song;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface SimpleSongMapper {

    @Mappings({
        @Mapping(source = "id", target = "songId"),
        @Mapping(source = "name", target = "songTitle")
    })
    @Named("songToSimpleSongDto")
    SimpleSongDto songToSimpleSongDto(Song track);

    @IterableMapping(qualifiedByName = "songToSimpleSongDto")
    List<SimpleSongDto> songsToSimpleSongDtos(List<Song> tracks);

}