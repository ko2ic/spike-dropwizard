package com.github.ko2ic.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.ko2ic.core.Person;
import com.github.ko2ic.db.PersonJdbiDao;
import com.sun.jersey.api.NotFoundException;

@Path("/people/jndi")
@Produces(MediaType.APPLICATION_JSON)
public class PeopleJdbiResource {

	private final PersonJdbiDao peopleDAO;

	public PeopleJdbiResource(PersonJdbiDao peopleDAO) {
		this.peopleDAO = peopleDAO;
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
		final Person person = peopleDAO.findById(personId.get());
		if (person == null) {
			throw new NotFoundException("{status:notfound}");
		}
		return person;
	}
}
