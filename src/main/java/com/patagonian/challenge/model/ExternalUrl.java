package com.patagonian.challenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExternalUrl {

    @JsonProperty("spotify")
    private String spotify;

}
