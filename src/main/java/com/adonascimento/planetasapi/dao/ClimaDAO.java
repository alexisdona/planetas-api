package com.adonascimento.planetasapi.dao;

import com.adonascimento.planetasapi.domain.Clima;
import com.adonascimento.planetasapi.dto.ClimaDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClimaDAO extends CrudRepository<Clima,Long> {

    @Query("select c from Clima c where c.dia = ?1")
    Clima findByDia(int dia);

    }

