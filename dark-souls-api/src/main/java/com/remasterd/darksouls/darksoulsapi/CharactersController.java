package com.remasterd.darksouls.darksoulsapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CharactersController {

    @Autowired
    CharactersService charactersService;


    @ExceptionHandler
    public ResponseEntity<String> handleExceptions(CharacterNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }



    // CREATE
    @PostMapping("/character")
    public ResponseEntity<Character> createCharacter(@RequestBody Character character) {
        charactersService.addCharacter(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(character);
    }



    // READ
    @GetMapping("/characters")
    public ResponseEntity<List<Character>> getCharacters(@RequestParam(required = false) String location, @RequestParam(required = false) String covenant, @RequestParam(required = false) String characterName) {

        if (location != null) {
            return ResponseEntity.status(HttpStatus.OK).body(charactersService.getCharactersByLocation(location));
        } else if (covenant != null) {
            return ResponseEntity.status(HttpStatus.OK).body(charactersService.getCharactersByCovenant(covenant));
        }else if (characterName != null) {
            return ResponseEntity.status(HttpStatus.OK).body(charactersService.getCharactersByName(characterName));
        }
        return ResponseEntity.status(HttpStatus.OK).body(charactersService.getAllCharacters());
    }


    @GetMapping("/characters/ids")
    public ResponseEntity<List<Integer>> getCharactersIds() {
        return ResponseEntity.status(HttpStatus.OK).body(charactersService.getCharacterIds());
    }


    @GetMapping("/characters/locations")
    public ResponseEntity<List<String>> getCharactersLocations() {
        return ResponseEntity.status(HttpStatus.OK).body(charactersService.getLocations());
    }

    @GetMapping("/characters/covenants")
    public ResponseEntity<List<String>> getCharactersCovenant() {
        return ResponseEntity.status(HttpStatus.OK).body(charactersService.getCovenants());
    }

    @GetMapping("/characters/names")
    public ResponseEntity<List<String>> getCharactersNames() {
        return ResponseEntity.status(HttpStatus.OK).body(charactersService.getNames());
    }

    @GetMapping("/character/{id}")
    public ResponseEntity<Character> getCharacterById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(charactersService.getCharacterById(id));
    }



    // UPDATE
    @PutMapping("/character/{id}")
    public ResponseEntity<Character> updateCharacter(@RequestBody Character newCharacter, @PathVariable int id) {
        newCharacter.setId(id);
        charactersService.updateCharacter(newCharacter, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(newCharacter);
    }



    // DELETE
    @DeleteMapping("/character/{id}")
    @Transactional
    public ResponseEntity<String> deleteCharacterById(@PathVariable int id) {
        charactersService.deleteCharacterById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("NPC has been deleted");
    }


}
