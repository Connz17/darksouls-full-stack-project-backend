package com.remasterd.darksouls.darksoulsapi;

import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name = "npc")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = new Random().nextInt();
    private String characterName;
    private String imageUrl;
    private String location;
    private String covenant;
    private String questline;
    private String status;

    //private String role;


    public Character() {
    }

    public Character(int id, String characterName, String imageUrl, String location, String covenant, String questline, String status) {
        this.id = id;
        this.characterName = characterName;
        this.imageUrl = imageUrl;
        this.location = location;
        this.covenant = covenant;
        this.questline = questline;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCovenant() {
        return covenant;
    }

    public void setCovenant(String covenant) {
        this.covenant = covenant;
    }

    public String getQuestline() {
        return questline;
    }

    public void setQuestLine(String questline) { this.questline = questline;}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

