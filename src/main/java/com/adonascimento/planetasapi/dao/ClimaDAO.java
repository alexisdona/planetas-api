package com.adonascimento.planetasapi.dao;

import com.adonascimento.planetasapi.domain.Clima;
import org.springframework.data.repository.CrudRepository;

public interface ClimaDAO extends CrudRepository<Clima,Long> {
}
