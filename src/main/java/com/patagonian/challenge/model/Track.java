package com.patagonian.challenge.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "tracks")
public class Track {

    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("track_number")
    @Field("track_number")
    private Integer trackNumber;

    @JsonProperty("type")
    private String type;

    @JsonProperty("disc_number")
    @Field("disc_number")
    private Integer discNumber;

    @JsonProperty("duration_ms")
    @Field("duration_ms")
    private Long durationMs;

    @JsonProperty("explicit")
    private Boolean explicit;

    @JsonProperty("external_urls")
    @Field("external_urls")
    private ExternalUrl externalUrls;

    @JsonProperty("href")
    private String href;

    @JsonProperty("is_local")
    @Field("is_local")
    private Boolean local;

    @JsonProperty("is_playable")
    @Field("is_playable")
    private Boolean playable;

    @JsonProperty("preview_url")
    @Field("preview_url")
    private String previewUrl;

    @JsonProperty("uri")
    private String uri;

    @JsonProperty("artists")
    private List<Artist> artists;

}
