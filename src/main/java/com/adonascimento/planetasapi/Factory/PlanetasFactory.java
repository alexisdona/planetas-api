package com.adonascimento.planetasapi.Factory;

import com.adonascimento.planetasapi.domain.Planeta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PlanetasFactory {


    public static Planeta betasoideBuilder() {
        return new Planeta("Betasoide",-3,2000);
    }

    public static Planeta ferengiBuilder() {
        return new Planeta("Ferengi",-1,500);
    }

    public static Planeta vulcanoBuilder() {
        return new Planeta("Vulcano",5,1000);
    }

    public static List<Planeta> planetasBuilder() {
        List<Planeta> planetas = new ArrayList<Planeta>();
        planetas.add(betasoideBuilder());
        planetas.add(ferengiBuilder());
        planetas.add(vulcanoBuilder());
        return planetas;
    }

}
