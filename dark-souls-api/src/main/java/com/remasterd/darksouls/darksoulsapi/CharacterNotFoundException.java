package com.remasterd.darksouls.darksoulsapi;

public class CharacterNotFoundException extends RuntimeException{

    public CharacterNotFoundException(){
        super("NPC has not been found");
    }
}
