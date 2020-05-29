package com.patagonian.challenge.mapper;

import com.patagonian.challenge.dto.ExternalUrlDto;
import com.patagonian.challenge.model.ExternalUrl;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExternalUrlMapper {

    ExternalUrlDto externalUrlToExternalUrlDto(ExternalUrl track);

}