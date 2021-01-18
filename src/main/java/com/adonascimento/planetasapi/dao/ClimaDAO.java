package com.adonascimento.planetasapi.dao;

import com.adonascimento.planetasapi.domain.Clima;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface ClimaDAO extends CrudRepository<Clima,Long> {

    @Query("select c from Clima c where c.dia = ?1")
    Clima findByDia(int dia);

    }

