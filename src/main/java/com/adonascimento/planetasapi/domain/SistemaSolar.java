package com.adonascimento.planetasapi.domain;


import com.adonascimento.planetasapi.Exceptions.SistemaSolarException;
import com.adonascimento.planetasapi.Factory.PlanetasFactory;
import com.adonascimento.planetasapi.calculation.Linea;
import com.adonascimento.planetasapi.calculation.Triangulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SistemaSolar {

    private static final int DIAS_POR_ANIO = 360;

    @Autowired
    private PlanetasFactory planetasFactory;

    @Autowired
    Linea linea;

    private List<Planeta> planetas;

    public SistemaSolar() {
        planetas = planetasFactory.planetasBuilder();
    }

    private List<Planeta> getPlanetas() {
        return planetas;
    }

    public boolean esDiaLluvioso(Integer dia){
        this.validateParams(dia);
        boolean centroIncluido;
        List<Punto> puntos = this.getPuntos(dia,this.getPlanetas());
        Triangulo triangulo = new Triangulo(
                puntos.get(0).getPosicionX(),
                puntos.get(0).getPosicionY(),
                puntos.get(1).getPosicionX(),
                puntos.get(1).getPosicionY(),
                puntos.get(2).getPosicionX(),
                puntos.get(2).getPosicionY());
       centroIncluido =  triangulo.centroIncluido();
       return centroIncluido;
    }

    public boolean esDiaSeco(int dia) {
        this.validateParams(dia);
         List<Planeta> planetas = this.getPlanetas();
         double posicionY0 = Math.abs(Math.sin(planetas.get(0).getAnguloBarrido(dia)));
            for(int i=1;i<planetas.size();i++){
                if (Math.abs(Math.sin(planetas.get(i).getAnguloBarrido(dia)))!=posicionY0){
                    return false;
                }
            }
                return true;

        }

        private List<Punto> getPuntos(int dia,List<Planeta> planetas) {
        return planetas.stream().map(planeta ->planeta.getPuntoActual(dia)).collect(Collectors.toList());
    }

    public boolean esDiaOptimo(int dia) {
        this.validateParams(dia);
        List<Punto> puntos = this.getPuntos(dia,this.getPlanetas());
        double pendiente = linea.calcularPendiente(puntos.get(0),puntos.get(1));
        double interseccionY = puntos.get(1).getPosicionY()-puntos.get(0).getPosicionY();
        if(linea.perteneceARecta(new Punto(0,0),interseccionY,pendiente)) {
            return false;
        }
        for(int i=2;i<puntos.size();i++){
            if (!linea.perteneceARecta(puntos.get(i),interseccionY,pendiente)){
                return false;
            }
        }
        return true;

    }

    public double getMilimetrosLluvia(Integer dia){
        this.validateParams(dia);
        if (esDiaLluvioso(dia)){
            List<Punto> puntos = this.getPuntos(dia,this.getPlanetas());
            Triangulo triangulo = new Triangulo(
                    puntos.get(0).getPosicionX(),
                    puntos.get(0).getPosicionY(),
                    puntos.get(1).getPosicionX(),
                    puntos.get(1).getPosicionY(),
                    puntos.get(2).getPosicionX(),
                    puntos.get(2).getPosicionY());
            return triangulo.perimetroTriangulo();
        }
        return 0;

    }

    private void validateParams(Integer anios) {
        if (anios==null) {
            throw new SistemaSolarException("Debe completar el dia a procesar");
        }

    }
}
