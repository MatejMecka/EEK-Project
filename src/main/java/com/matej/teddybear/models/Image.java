package com.matej.teddybear.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table( name = "images" )
public class Image {

    /**
     * ID in the Database object
     */
    @Id
    Long id;

    /**
     * The File Name for an image
     */
    private String fileName;

    /**
     * Image contents of a Teddy Bear
     */
    private byte[] fileContents;

}
