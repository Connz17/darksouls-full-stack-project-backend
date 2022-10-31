package com.remasterd.darksouls.darksoulsapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharactersRepository extends JpaRepository<Character, Integer> {

    @Query(value = "SELECT DISTINCT id FROM character", nativeQuery = true)
    List<Integer> getDistinctIds();

    List<Character> getAllByLocation(String location);
    List<Character> getAllByCharacterName(String characterName);
    List<Character> getAllByCovenant(String covenant);


    @Query(value="SELECT DISTINCT location FROM character ORDER BY location", nativeQuery = true)
    List<String> getDistinctLocations();

    @Query(value="SELECT DISTINCT covenant FROM character ORDER BY covenant", nativeQuery = true)
    List<String> getDistinctCovenants();

    void deleteCharacterById(Integer id);
}

