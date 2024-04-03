package com.matej.teddybear.repository;

import com.matej.teddybear.models.Teddy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNullApi;

import java.util.List;

/**
 * A CrudRepository handle for All Teddy Objects.
 * <p>It contains a List function to Find All of them</p>
 */
public interface TeddyRepository extends CrudRepository<Teddy, Long> {

    List<Teddy> findAll();
    Teddy findByTeddyBarcode(Long teddyBarcode);
}
