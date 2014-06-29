package com.github.ko2ic.db;

import io.dropwizard.hibernate.AbstractDAO;

import java.util.List;

import org.hibernate.SessionFactory;

import com.github.ko2ic.core.Person;
import com.google.common.base.Optional;

public class PersonRepository extends AbstractDAO<Person> {
    public PersonRepository(SessionFactory factory) {
        super(factory);
    }

    public Optional<Person> findById(Long id) {
        return Optional.fromNullable(get(id));
    }

    public Person create(Person person) {
        return persist(person);
    }

    public List<Person> findAll() {
        return list(namedQuery("com.github.ko2ic.core.Person.findAll"));
    }
}
