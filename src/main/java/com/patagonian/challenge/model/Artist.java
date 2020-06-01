package com.patagonian.challenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artist {

    @JsonProperty("id")
    @Field("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("href")
    private String href;

    @JsonProperty("uri")
    private String uri;

    @JsonProperty("external_urls")
    @Field("external_urls")
    private ExternalUrl externalUrls;

}
