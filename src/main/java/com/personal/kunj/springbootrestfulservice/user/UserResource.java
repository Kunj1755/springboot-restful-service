package com.personal.kunj.springbootrestfulservice.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService service;

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("id-" + id);
		}
		// HATEOAS
		// Creating resource around the user
		Resource<User> resource = new Resource<User>(user);
		/*
		 * Now add links to the resource. But before this, get the links for
		 * retrieveAllUsers(). We are getting the links for retrieveAllUsers() bcz we do
		 * not want to hard code the path to "/users". ControllerLinkBuilder class helps
		 * us in creating links from methods
		 */
		ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(this.getClass(), retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}

	// Input --> Details of the new user
	// output --> CREATED (status) and URI of the created resource (Location header
	// will have the uri of the new resource)
	@PostMapping(path = "/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User newUser = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);

		if (user == null)
			throw new UserNotFoundException("id-" + id);
	}

}
