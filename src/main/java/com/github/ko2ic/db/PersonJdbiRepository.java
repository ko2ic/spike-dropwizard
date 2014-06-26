package com.github.ko2ic.db;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import com.github.ko2ic.core.Person;

public interface PersonJdbiRepository {

	@SqlQuery("select id, fullName, jobTitle from people where id = :id")
	@Mapper(PersonJdbiMapper.class)
	public Person findById(@Bind("id") Long id);

	@SqlQuery("select id, fullName, jobTitle from people")
	@Mapper(PersonJdbiMapper.class)
	public List<Person> findAll();
}
