package com.recommendationService.repositories;


import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import com.recommendationService.domain.Game;
import com.recommendationService.domain.User;


/**
 * Created by jt on 1/10/17.
 */
public interface UserRepository extends Neo4jRepository<User, Long> {
	@Query("match (n:User)-[:friends]->(f:User) where ID(n)={id} return f;")
	List<User> frindOfUser(@Param("id") long id);
	@Query("match (n:User)-[:friends]->(f:User)-[:plays]->(g:Game) where ID(n)={id} return g;")
	List<Game> gamePlayedByFrindOfUser(@Param("id") long id);
}
