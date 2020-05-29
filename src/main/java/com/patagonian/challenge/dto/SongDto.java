package com.patagonian.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SongDto {

    private String id;

    private String name;

    @JsonProperty("track_number")
    private Integer trackNumber;

    private String type;

    @JsonProperty("disc_number")
    private Integer discNumber;

    @JsonProperty("duration_ms")
    private Long durationMs;

    private Boolean explicit;

    @JsonProperty("external_urls")
    private ExternalUrlDto externalUrlDto;

    private String href;

    @JsonProperty("is_local")
    private Boolean local;

    @JsonProperty("is_playable")
    private Boolean playable;

    @JsonProperty("preview_url")
    private String previewUrl;

    private String uri;

    @JsonProperty("artists")
    private List<ArtistDto> artistDtos;

}