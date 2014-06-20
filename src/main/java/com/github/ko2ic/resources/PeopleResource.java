package com.github.ko2ic.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.ko2ic.core.Person;
import com.github.ko2ic.db.PersonDao;
import com.google.common.base.Optional;
import com.sun.jersey.api.NotFoundException;

@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
public class PeopleResource {

	private final PersonDao peopleDAO;

	public PeopleResource(PersonDao peopleDAO) {
		this.peopleDAO = peopleDAO;
	}

	@POST
	@UnitOfWork
	public Person createPerson(Person person) {
		return peopleDAO.create(person);
	}

	@GET
	@UnitOfWork
	public List<Person> listPeople() {
		return peopleDAO.findAll();
	}

	@GET
	@UnitOfWork
	@Path("/{personId}")
	public Person getPerson(@PathParam("personId") LongParam personId) {
		final Optional<Person> person = peopleDAO.findById(personId.get());
		if (!person.isPresent()) {
			throw new NotFoundException("{status:notfound}");
		}
		return person.get();
	}
}
