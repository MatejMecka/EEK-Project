package com.matej.teddybear.controllers;

import com.matej.teddybear.api_messages.Messages;
import com.matej.teddybear.handlers.ImageDataHandler;
import com.matej.teddybear.models.Image;
import com.matej.teddybear.repository.ImageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/images")
@CrossOrigin(origins = {"http://localhost:5173", "http://127.0.01:5173"})
public class ImageControllers {

    private final ImageRepository imageRepository;
    ImageDataHandler imageDataHandler;

    public ImageControllers(ImageRepository imageRepository, ImageDataHandler imageDataHandler) {
        this.imageRepository = imageRepository;
        this.imageDataHandler = imageDataHandler;
    }

    /**
     * Return an Image in Base64
     * @param imageId The ID in the database
     * @return Image The image object from the Database
     */
    @GetMapping("{imageId}")
    public Image readImage(@PathVariable Long imageId) {
       Optional<Image> image = imageRepository.findById(imageId);
        if (image.isPresent()){
            return image.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, Messages.IMAGE_NOT_FOUND);
    }

    /**
     * Create an Image in a Database
     * @param file MultipartFile The image file sent in CURL
     * @return String A Success or Error Message
     * @throws IOException IO Exception when opening the file
     */

    @PostMapping("create")
    public Long createImage(@RequestParam("image") MultipartFile file) throws IOException {
        return imageDataHandler.uploadImage(file);
    }

    /**
     * Update an Existing image in a Database
     * @param file The File that we want to replace it with
     * @param image_id The image ID in the database
     * @return String Success Message or Error Message
     */
    @PatchMapping("{image_id}")
    public String patchImage(@RequestParam("image") MultipartFile file, @PathVariable Long image_id) throws IOException {
        return imageDataHandler.updateImage(file, image_id);
    }

    /**
     * Delete an Image
     * @param image_id The image ID in the database
     * @return String Return a Success or Error Message
     */
    @DeleteMapping("{image_id}")
    public String deleteImage(@PathVariable Long image_id) {
        String response = imageDataHandler.deleteImage(image_id);
        if(!Objects.equals(response, Messages.SUCCESS_DELETING_IMAGE)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, response);
        } else {
            return response;
        }
    }

}
