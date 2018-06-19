package com.recommendationService.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.recommendationService.commands.UserForm;
import com.recommendationService.domain.User;

/**
 * Created by jt on 1/10/17.
 */
@Component
public class UserToUserForm implements Converter<User, UserForm> {
    @Override
    public UserForm convert(User user) {
        UserForm userForm = new UserForm();
        userForm.setId(user.getId());
        userForm.setName(user.getName());
        
        return userForm;
    }

	
}
