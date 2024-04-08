package com.matej.teddybear.handlers;

import com.matej.teddybear.api_messages.Messages;
import com.matej.teddybear.models.Image;
import com.matej.teddybear.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageDataHandler {

    private final ImageRepository imageRepository;


    /**
     * Constructor to initialize imageRepogistory
     * @param imageRepository the repository for images
     */
    @Autowired
    public ImageDataHandler(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    /**
     *
     * @param imageId The ID in the database belonging to the image
     * @return String Message indicating if successful or not
     */
    public String deleteImage(Long imageId) {
        Optional<Image> image = imageRepository.findById(imageId);
        if (image.isPresent()){
            try {
                imageRepository.delete(image.get());
                return Messages.SUCCESS_DELETING_IMAGE;
            } catch (Error err){
                System.out.println(err);
                return Messages.DB_ERROR;
            }
        }
        return null;
    }

    /**
     *
     * @param file The raw bytes of the image
     * @return String Success or Error Message
     * @throws IOException If the image fails to be opened or written
     */
    public String uploadImage(MultipartFile file) throws IOException {
            Image new_image = new Image();
            new_image.setFileName(file.getOriginalFilename());
            new_image.setFileContents(file.getBytes());
            imageRepository.save(new_image);
            return Messages.IMAGE_SAVED;
    }

    /**
     *
     * @param file the raw bytes of the image.
     * @param id The Id in the Database
     * @return String Success or Error Message
     * @throws IOException When it fails to open or save the image in the database.
     */
    public String updateImage(MultipartFile file, Long id) throws IOException {
        Optional<Image> image = imageRepository.findById(id);
        if(image.isPresent()) {
            image.get().setFileName(file.getOriginalFilename());
            image.get().setFileContents(file.getBytes());
            try {
                imageRepository.save(image.get());
                return Messages.IMAGE_UPDATED;
            } catch (Error error){
                System.out.println(error);
                return Messages.DB_ERROR;
            }
        } else {
            return Messages.IMAGE_NOT_FOUND;
        }
    }
}
