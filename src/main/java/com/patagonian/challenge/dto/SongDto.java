package com.patagonian.challenge.dto;

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
    private Integer trackNumber;
    private String type;
    private Integer discNumber;
    private Long durationMs;
    private Boolean explicit;
    //private ExternalUrl externalUrls;
    private String href;
    private Boolean local;
    private Boolean playable;
    private String previewUrl;
    private String uri;
    //private List<Artist> artists;

}