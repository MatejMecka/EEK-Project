package com.matej.teddybear.repository;

import com.matej.teddybear.models.Image;
import org.springframework.data.repository.CrudRepository;

/**
 * A CrudRepository handle for All Images related things
 */
public interface ImageRepository extends CrudRepository<Image, Long> {

}
