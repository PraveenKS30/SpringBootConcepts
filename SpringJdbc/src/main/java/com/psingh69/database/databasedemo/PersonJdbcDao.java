package com.psingh69.database.databasedemo;

import com.psingh69.database.databasedemo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class PersonJdbcDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // return all results from a query
    public List<Person> findAll() {
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));

    }

    // return single result at a time
    public Person findbyId (int id){
        return jdbcTemplate.queryForObject("select * from person where id = ?",  new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class));
    }

    // delete records by id using jdbcTemplate.update, returns int --> number of records affected
    public int deletebyId (int id){
        return jdbcTemplate.update("delete from person where id = ? ", new Object[]{id});
    }


    // insert into table
    public  int addPerson (Person person){
        return jdbcTemplate.update("insert into person( id, name, location, birthdate) values (? , ?, ?, ?)",
                                                            new Object[] {
                                                                                    person.getId(), person.getName(),
                                                                                    person.getLocation(),
                                                                                    new Timestamp(person.getBirthdate().getTime())
                                                                                });

    }

    //update into table
    public int updatePerson(Person person){
        return jdbcTemplate.update("update person set name = ?, location = ?, birthdate = ? where id = ?",
                                                            new Object[]{
                                                                                    person.getName(), person.getLocation(),
                                                                                    new Timestamp(person.getBirthdate().getTime()),
                                                                                    person.getId()
                                                                            });
    }
}