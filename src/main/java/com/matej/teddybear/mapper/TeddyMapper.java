package com.matej.teddybear.mapper;
import com.matej.teddybear.models.Teddy;

import java.util.Date;

public class TeddyMapper {

    public static Teddy teddyUpdater(Teddy original_teddy, Teddy modified) {
        return ReassignTeddy(original_teddy, modified.getName(), modified.getDescription(), modified.getCity(), modified.getLatitude(), modified.getLongitude(), modified.getImageId(), modified.getDateReceived(), modified.getReceivedFrom());
    }

    private static Teddy ReassignTeddy(Teddy teddy, String name, String description, String city, Double latitude, Double longitude, Long imageId, Date dateReceived, String receivedFrom) {
        teddy.setName(name);
        teddy.setDescription(description);
        teddy.setCity(city);
        teddy.setLatitude(latitude);
        teddy.setLongitude(longitude);
        teddy.setImageId(imageId);
        teddy.setDateReceived(dateReceived);
        teddy.setReceivedFrom(receivedFrom);
        return teddy;
    }
}
