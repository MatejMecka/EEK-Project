package com.matej.teddybear.handlers;

import com.matej.teddybear.models.Teddy;
import com.matej.teddybear.repository.TeddyRepository;
import com.matej.teddybear.api_messages.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.matej.teddybear.mapper.TeddyMapper.teddyUpdater;

@Service
public class TeddyDataHandler {

    private final TeddyRepository teddyRepository;

    @Autowired
    public TeddyDataHandler(TeddyRepository teddyRepository) {
        this.teddyRepository = teddyRepository;
    }

    /**
     * Create a New Teddy Bear Object in the Database
     * @param teddy Teddy Bear Object containing the data
     * @return String Either a Success Message, or Error Message
     */
    public String NewTeddyBear(Teddy teddy) {
        try {
            teddyRepository.save(teddy);
            return Messages.SUCCESS_SAVING_TEDDY_BEAR;
        } catch (Exception error) {
            System.out.println(error.toString());
            return Messages.DB_ERROR;
        }
    }

    /**
     * Update the Teddy Bear Object in the database
     * @param originalTeddy The Original Teddy Object in the database
     * @param modified The modified data fetched from the Request
     * @return String Return a Success or Error Message
     */
    public String UpdateTeddyBear(Teddy originalTeddy, Teddy modified) {
        // Update All fields to the original one
        try {
            Teddy teddyUnique = teddyRepository.findByTeddyBarcode(modified.getTeddyBarcode());
            Teddy teddyUniqueOriginal = teddyRepository.findByTeddyBarcode(originalTeddy.getTeddyBarcode());
            if(teddyUnique != null && teddyUniqueOriginal != null && !Objects.equals(teddyUnique.getId(), teddyUniqueOriginal.getId())) {
                return Messages.TEDDY_BEAR_COLLISSION;
            } else if(!Objects.equals(originalTeddy.getTeddyBarcode(), modified.getTeddyBarcode())) {
                teddyRepository.save(teddyUpdater(originalTeddy, modified));
                originalTeddy.setTeddyBarcode(modified.getTeddyBarcode());
                teddyRepository.save(teddyUpdater(originalTeddy, modified));
            } else {
                teddyRepository.save(teddyUpdater(originalTeddy, modified));
            }
            return Messages.SUCCESS_UPDATING_TEDDY_BEAR;
        } catch (Exception error) {
            System.out.println(error);
            return Messages.DB_ERROR;
        }
    }

    /**
     * Delete the Teddy Bear from the Database
     * @param teddy_id The Barcode for the Teddy Bear
     * @return String Return a Success Message or Error Message
     */
    public String deleteTeddy(Long teddy_id) {
        Teddy teddy = teddyRepository.findByTeddyBarcode(teddy_id);
        if (teddy != null){
            try {
                teddyRepository.delete(teddy);
                return Messages.SUCCESS_DELETING_TEDDY_BEAR;
            } catch (Error err){
                System.out.println(err);
                return Messages.DB_ERROR;
            }
        }
        return null;
    }
}

