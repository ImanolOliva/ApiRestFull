package com.example.neoris.Service;


import com.example.neoris.Model.Jornada;
import com.example.neoris.Repository.RepositorioJornada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

import static java.util.Objects.isNull;

@Service
public class Validaciones {

    private final long VACACIONES = 5;
    private final long DIA_LIBRE = 3;
    private final long HORAS_EXTRA = 2;
    private final long JORNADA_NORMAL = 4;


    @Autowired
    private RepositorioJornada jornada;
    @Autowired
    private ServicioTipoJornadaLaboral tipoJornadaServicio;

    private boolean duracionDeLaJornadaLaboral(Jornada nuevaJornada) {
        long duracionJornada = this.obtenerDuracionDeJornada(nuevaJornada);

        if (nuevaJornada.getTipoJornadaLaboral().getId() == JORNADA_NORMAL) {
            return duracionJornada >= 6 && duracionJornada <= 8;
        } else if (nuevaJornada.getTipoJornadaLaboral().getId() == HORAS_EXTRA) {
            return duracionJornada >= 2 && duracionJornada <= 6;
        }
        return true;
    }

    private long obtenerDuracionDeJornada(Jornada jornada) {
        return ChronoUnit.HOURS.between(jornada.getHoraDeEntrada(), jornada.getHoraDeSalida());
    }

    private boolean esCantidadDeHorasTrabajadasValidasPorDia(Jornada nuevaJornada) {
        // Si el empleado esta de vacaciones, no importa las horas que se le sumen al dia
        if (empleadoNoEstaDeVacacionesNiDiaLibre(nuevaJornada)) {
            return true;
        }
        return false;
    }

    private boolean empleadoNoEstaDeVacacionesNiDiaLibre(Jornada jornada) {
        return !jornada.getTipoJornadaLaboral().getId().equals(DIA_LIBRE) && !jornada.getTipoJornadaLaboral().getId().equals(VACACIONES);
    }

    private boolean empleadoDiaLibre(Jornada nuevaJornada) {
        Boolean diaLibre = nuevaJornada.getId().equals(DIA_LIBRE);
        if(diaLibre == true){
            return isNull(diaLibre);
        }
        return false;
    }
}