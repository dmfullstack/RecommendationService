package com.recommendationService.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.recommendationService.commands.UserForm;
import com.recommendationService.converters.UserToUserForm;
import com.recommendationService.domain.User;
import com.recommendationService.services.UserService;


import javax.validation.Valid;

/**
 * Created by jt on 1/10/17.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
@RestController
public class UserController {
	
    private UserService userService;           

    private UserToUserForm userToUserForm;
    
    Logger log= LoggerFactory.getLogger(UserController.class);

    @Autowired
    public void setProductToProductForm(UserToUserForm userToUserForm) {
        this.userToUserForm = userToUserForm;
    }

    @Autowired
    public void setProductService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/recommendation/friendGame/{id}")
	public ResponseEntity<String> getFriendGame(User user, @PathVariable("id") String id) {

		String output = userService.friendPlayedGame(Long.parseLong(id));
		
		return new ResponseEntity<String>(output, HttpStatus.OK);
		
	}
    
    @GetMapping("/recommendation")
	public ResponseEntity getAllUser() {

		Iterable<User> user = userService.listAllUser();
		
		return new ResponseEntity<>(user, HttpStatus.OK);
		
	}

    
	@GetMapping("/recommendation/{id}")
	public ResponseEntity<User> findUserById(User user, @PathVariable("id") String id) {
		
		user.setId(Long.parseLong(id));
		
		log.info(id);
		
		User user1 = userService.getById(Long.parseLong(id));
		
		return new ResponseEntity<User>(user1, HttpStatus.OK);
		
	}
	
	@PostMapping("/recommendation")
	public ResponseEntity<User> addUser(@RequestBody User user) throws Exception {
		
		User user1 = userService.saveOrUpdate(user);
		
		log.info("user save");
		
		return new ResponseEntity<User>(user1, HttpStatus.OK);
		
	}

	@PutMapping("/recommendation/{id}")
	public ResponseEntity<User> updateUserUsingId(@RequestBody User user, @PathVariable("id") String id) {
		
		user.setId(Long.parseLong(id));
		
		User user1 = userService.saveOrUpdate(user);
		
		return new ResponseEntity<User>(user1, HttpStatus.OK);
		
	}

	@DeleteMapping("/recommendation/{id}")
	public ResponseEntity<User> deleteUserUsingId(User user) {

		userService.delete(user.getId());
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}

   

//    @RequestMapping("/")
//    public String redirToList(){
//        return "redirect:/user/list.html";
//    }
//
//    @RequestMapping({"/user/list", "/user"})
//    public String listProducts(Model model){
//        model.addAttribute("users", userService.listAllUser());
//        System.out.println(userService.listAllGame());
//        return "user/list";
//    }
//
//    @RequestMapping("/user/show/{id}")
//    public String getProduct(@PathVariable String id, Model model){
//    	System.out.println(id);
//    	
//        model.addAttribute("user", userService.getById(Long.valueOf(id)));
//        return "user/show";
//    }
//
//    @RequestMapping("user/edit/{id}")
//    public String edit(@PathVariable String id, Model model){
//    	System.out.println(id);
//        User user = userService.getById(Long.valueOf(id));
//        UserForm userForm = userToUserForm.convert(user);
//
//        model.addAttribute("userForm", userForm);
//        return "user/userform";
//    }
//
//    @RequestMapping("/user/new")
//    public String newProduct(Model model){
//        model.addAttribute("userForm", new UserForm());
//        return "user/userform";
//    }
//
//    @RequestMapping(value = "/user", method = RequestMethod.POST)
//    public String saveOrUpdateProduct(@Valid UserForm userForm, BindingResult bindingResult){
//
//        if(bindingResult.hasErrors()){
//            return "user/userform";
//        }
//
//        User savedUser = userService.saveOrUpdateProductForm(userForm);
//
//        return "redirect:/user/show/" + savedUser.getId();
//    }
//
//    @RequestMapping("/user/delete/{id}")
//    public String delete(@PathVariable String id){
//    	userService.delete(Long.valueOf(id));
//        return "redirect:/user/list";
//    }
}
