package com.patagonian.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ArtistDto {

    private String id;

    private String name;

    private String type;

    private String href;

    private String uri;

    @JsonProperty("external_urls")
    private ExternalUrlDto externalUrlDto;

}
