package com.remasterd.darksouls.darksoulsapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CharactersService {

    @Autowired
    CharactersRepository charactersRepository;

    //CREATE
    public void addCharacter(Character character){
        charactersRepository.save(character);
    }

    // READ
    public Character getCharacterById(int id){
        Optional<Character> character = charactersRepository.findById(id);

        if (character.isEmpty()){
            throw new CharacterNotFoundException();
        }
        return character.get();
    }

    public List<Integer> getCharacterIds(){
        return charactersRepository.getDistinctIds();
    }

    public List<String> getNames(){
        List<Character> characters = charactersRepository.findAll();

        return characters.stream()
                .map(Character::getCharacterName)
                .collect(Collectors.toList());
    }

    public List<String> getLocations(){
        List<Character> characters = charactersRepository.findAll();

        return characters.stream()
                .map(Character::getLocation)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> getCovenants(){
        List<Character> characters = charactersRepository.findAll();

        return characters.stream()
                .map(Character::getCovenant)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Character> getCharactersByName(String characterName) {
        List<Character> characters = charactersRepository.getAllByCharacterName(characterName);

        return characters.stream()
                .filter(character -> character.getCharacterName().equalsIgnoreCase(characterName))
//                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<Character> getCharactersByLocation(String location) {
        List<Character> characters = charactersRepository.getAllByLocation(location);

        return characters.stream()
                .filter(character -> character.getLocation().equalsIgnoreCase(location))
//                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<Character> getCharactersByCovenant(String covenant) {
        List<Character> characters = charactersRepository.getAllByCovenant(covenant);

        return characters.stream()
                .filter(character -> character.getCovenant().equalsIgnoreCase(covenant))
//                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<Character> getAllCharacters() {
        return charactersRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    // UPDATE
    public void updateCharacter(Character newCharacter, int id) {
        if (!charactersRepository.existsById(id)) {
            throw new CharacterNotFoundException();
        }
        newCharacter.setId(id);
        charactersRepository.save(newCharacter);
    }

    // DELETE
    @Transactional
    public void deleteCharacterById(int id) {
        if (!charactersRepository.existsById(id)) {
            throw new CharacterNotFoundException();
        }

        charactersRepository.deleteCharacterById(id);
    }




}
