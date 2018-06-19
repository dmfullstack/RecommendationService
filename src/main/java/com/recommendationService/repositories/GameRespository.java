package com.recommendationService.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.recommendationService.domain.Game;


public interface GameRespository extends Neo4jRepository<Game, Long>{

}
