package com.matej.teddybear.repository;

import com.matej.teddybear.models.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Long> {

}
