package com.patagonian.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDto {
  private String id;

  private String name;

  private String type;

  private String href;

  private String uri;

  @JsonProperty("external_urls")
  private ExternalUrlDto externalUrlDto;
}
