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
public class ImageControllers {

    private final ImageRepository imageRepository;
    ImageDataHandler imageDataHandler;

    public ImageControllers(ImageRepository imageRepository, ImageDataHandler imageDataHandler) {
        this.imageRepository = imageRepository;
        this.imageDataHandler = imageDataHandler;
    }

    @GetMapping("{imageId}")
    public Image readImage(@PathVariable Long imageId) {
       Optional<Image> image = imageRepository.findById(imageId);
        if (image.isPresent()){
            return image.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, Messages.IMAGE_NOT_FOUND);
    }

    @PostMapping("create")
    public String createImage(@RequestParam("image") MultipartFile file) throws IOException {
        return imageDataHandler.uploadImage(file);
    }

    @PatchMapping("{image_id}")
    public String patchImage(@RequestParam("image") MultipartFile file, @PathVariable Long image_id) {
        return "hi";
    }

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
