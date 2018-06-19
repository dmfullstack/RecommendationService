package com.recommendationService.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.recommendationService.commands.UserForm;
import com.recommendationService.domain.User;

/**
 * Created by jt on 1/10/17.
 */
@Component
public class UserFormToUser implements Converter<UserForm, User> {

    @Override
    public User convert(UserForm userForm) {
        User user = new User();
        if (userForm.getId() != null  && !StringUtils.isEmpty(userForm.getId())) {
            user.setId(new Long(userForm.getId()));
        }
        user.setName(userForm.getName());
        
        return user;
    }
}
