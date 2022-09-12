package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;


@Getter@Setter@ToString
@NoArgsConstructor@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

    @JsonProperty("location_id")
    private int locationID;

    private String city;

    @JsonProperty("country_id")
    private String countryID;

    private List<Link> links;

    // What is the relation between link and location ? (  IS -A  vs HAS -A RelationShip  )
    // Has a relationship


}
