package com.matej.teddybear.controllers;

import com.matej.teddybear.api_messages.Messages;
import com.matej.teddybear.handlers.TeddyDataHandler;
import com.matej.teddybear.models.Teddy;
import com.matej.teddybear.repository.TeddyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

/**
 * Rest Controller responsible for handling all CRUD operations related to Teddy Bears
 * Accessible on /api/TeddyBear
 */
@RestController
@RequestMapping("/api/teddybear")
public class TeddyControllers {

    /**
     * Teddy Repository that interacts with the database.
     */
    private final TeddyRepository teddyRepository;
    TeddyDataHandler teddyDataHandler;

    public TeddyControllers(TeddyRepository teddyRepository, TeddyDataHandler teddyDataHandler) {
        this.teddyRepository = teddyRepository;
        this.teddyDataHandler = teddyDataHandler;
    }

    /**
     * Returns the information about a particular Teddy Bear given a barcode
     * @param teddy_id A Barcode
     * @return Teddy A Teddy object in its JSON Representation
     * @throws ResponseStatusException 404 If the Teddy Bear is not Found
     */
    @GetMapping("{teddy_id}")
    public Teddy readTeddy(@PathVariable Long teddy_id) {
        Teddy teddy = teddyRepository.findByTeddyBarcode(teddy_id);
        if (teddy != null){
            return teddy;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, Messages.TEDDY_NOT_FOUND);
    }

    /**
     * @param teddy A Request Body for Teddy
     * @return String message if the transaction succeeded
     */

    @PostMapping("create")
    public String createTeddy(@RequestBody Teddy teddy) {
        return teddyDataHandler.NewTeddyBear(teddy);
    }
    /**
     * Update a Teddy Bear's information
     * @param teddy Information about the Teddy Bear
     * @param teddy_id Barcode we are updating
     * @return String Message if the transaction succeeded
     */
    @PatchMapping("{teddy_id}")
    public String patchTeddy(@RequestBody Teddy teddy, @PathVariable Long teddy_id) {
        Teddy teddy_original = teddyRepository.findByTeddyBarcode(teddy_id);
        System.out.println(teddy_original);
        if (teddy_original != null){
            return teddyDataHandler.UpdateTeddyBear(teddy_original, teddy);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, Messages.TEDDY_NOT_FOUND);
    }


    /**
     * Deletes a Particular Teddy from the database provided a Barcode
     * @param teddy_id The Teddy's Barcode
     * @return String Response Status
     * @throws ResponseStatusException Returns 404 if the particular Teddy does not exist
     */
    @DeleteMapping("{teddy_id}")
    public String deleteTeddy(@PathVariable Long teddy_id) {
        String response = teddyDataHandler.deleteTeddy(teddy_id);
        if(!Objects.equals(response, Messages.SUCCESS_DELETING_TEDDY_BEAR)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, response);
        } else {
            return response;
        }

    }

    /**
     * Give a List of all Teddies in a JSON representation
     * @return List<Teddy> A List of all the Teddy's stored in the database.
     */
    @GetMapping("all")
    public List<Teddy> all() {
        return teddyRepository.findAll()
                .stream()
                .toList();
    }

}
