package com.recommendationService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recommendationService.commands.UserForm;
import com.recommendationService.converters.UserFormToUser;
import com.recommendationService.domain.Game;
import com.recommendationService.domain.User;
import com.recommendationService.repositories.GameRespository;
import com.recommendationService.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jt on 1/10/17.
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserFormToUser userFormToUser;
    private GameRespository gameRepository;

    @Autowired
    public UserServiceImpl(UserRepository productRepository, UserFormToUser productFormToProduct,GameRespository gameRepository) {
        this.userRepository = productRepository;
        this.userFormToUser = productFormToProduct;
        this.gameRepository=gameRepository;
    }


    @Override
    public List<User> listAllUser() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add); //fun with Java 8
        return users;
    }

    @Override
    public User getById(Long id) {
    	
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User saveOrUpdate(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);

    }

    @Override
    public User saveOrUpdateProductForm(UserForm userForm) {
        User savedUser = saveOrUpdate(userFormToUser.convert(userForm));

        System.out.println("Saved User Id: " + savedUser.getId());
        return savedUser;
    }
    
    @Override
    public List<Game> listAllGame() {
        List<Game> games = new ArrayList<>();
        gameRepository.findAll().forEach(games::add); //fun with Java 8
        return games;
    }


	@Override
	public String friendPlayedGame(long id) {
		String output="";
		List<User> u=userRepository.frindOfUser(id);
		List<Game> g=userRepository.gamePlayedByFrindOfUser(id); 
		
		if(u.size() >0)
		{
    	for(int i=0;i<u.size();i++)
    	{
    		output=u.get(i).getName()+" plays";
    		
    	}
			if(g.size()!=0)
			{
    	for(int i=0;i<g.size();i++)
    	{
    		output=output+" "+g.get(i).getName();
    		
    	}
			}
			else
			{
				output=output+" no game";
			}
		}
		else
		{
			output="you have no friends";
		}
		return output;
	}
}
