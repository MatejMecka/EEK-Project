package com.matej.teddybear.models;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

/**
 * Teddy representation and all it's important information
 */
@Data
@Table(name = "teddies")

public class Teddy {

    /**
     * An ID we keep track in the database.
     */
    @Id
    private Long id;

    /**
     * A Barcode Number of a Teddy, that we can use to query the Teddy
     */
    private Long teddyBarcode;

    /**
     * The Name of our Teddy Bear
     */
    private String name;

    /**
     * The Date we have bought it or received it as a gift
     */
    private Date dateReceived;

    /**
     * The name of the person we have received as a gift or our name if we bought it
     */
    private String receivedFrom;

    /**
     * The City in Name. This is a Broad Short Field, for writing a descriptive name
     */
    private String city;

    /**
     * Double value for the Latitude, when we will display this on a Map
     */
    private Double latitude;

    /**
     * Double value for the Longitude, when we will display this on a Map/
     */
    private Double longitude;

    /**
     * The Description we want to show on a specific Teddy Bear.
     * Notes, stories, and so on.
     */
    private String description;

    /**
     * The Image ID that we can use to retrieve the Image from
     */
    private Long imageId;


}
