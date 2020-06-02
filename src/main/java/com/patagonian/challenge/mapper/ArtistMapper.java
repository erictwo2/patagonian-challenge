package com.patagonian.challenge.mapper;

import java.util.List;

import com.patagonian.challenge.dto.ArtistDto;
import com.patagonian.challenge.model.Artist;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ArtistMapper {

    @Mapping(source = "externalUrls", target = "externalUrlDto")
    @Named("artistToArtistDto")
    ArtistDto artistToArtistDto(Artist artist);

    @IterableMapping(qualifiedByName = "artistToArtistDto")
    List<ArtistDto> artistsToArtistDtos(List<Artist> artists);

}