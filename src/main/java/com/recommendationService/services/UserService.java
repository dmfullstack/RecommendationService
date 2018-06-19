package com.recommendationService.services;

import java.util.List;

import com.recommendationService.commands.UserForm;
import com.recommendationService.domain.Game;
import com.recommendationService.domain.User;

/**
 * Created by jt on 1/10/17.
 */
public interface UserService {

    List<User> listAllUser();
    
    List<Game> listAllGame();

    User getById(Long id);

    User saveOrUpdate(User user);

    void delete(Long id);

    User saveOrUpdateProductForm(UserForm userForm);
    
    String friendPlayedGame(long id);
}
