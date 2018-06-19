package com.recommendationService.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import java.util.Set;

/**
 * Created by jt on 1/10/17.
 */
@NodeEntity(label = "User")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    
    @Relationship(type="plays", direction = Relationship.INCOMING)
    Set<Game> games;
    
    @Relationship(type="friends", direction = Relationship.INCOMING)
    Set<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

   
}
