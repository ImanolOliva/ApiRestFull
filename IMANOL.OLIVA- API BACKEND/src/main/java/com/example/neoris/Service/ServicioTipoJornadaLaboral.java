package com.example.neoris.Service;

import com.example.neoris.Model.Jornada;
import com.example.neoris.Model.TipoJornadaLaboral;
import com.example.neoris.Repository.RepositorioTipoJornadaLaboral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.util.List;

@Service
public class ServicioTipoJornadaLaboral {


    /** Inyecto repositoruiDeJornadaLaboral**/
   @Autowired
    private RepositorioTipoJornadaLaboral repositorioTipoJornadaLaboral;

    /** Guardo tipo de jornada laboral **/
   public TipoJornadaLaboral guarTipoJornadaLaboral(TipoJornadaLaboral tipoJornadaLaboral){
       return repositorioTipoJornadaLaboral.save(tipoJornadaLaboral);
   }
    /** Muestro todos mis datos de TipoJornadaLaboral con el metodo FindAll**/
   public List<TipoJornadaLaboral> listarTipoJornadaLaboral(TipoJornadaLaboral tipoJornadaLaboral){
       return repositorioTipoJornadaLaboral.findAll();
   }

   /** Listo por id el tipo de jornada laboral **/
   public TipoJornadaLaboral listarTipoJornadaLaboralPorId(Long id){
       TipoJornadaLaboral tipo = repositorioTipoJornadaLaboral.findById(id).get();
       return tipo;
   }

   public  TipoJornadaLaboral buscarJornadaPorNombre(String nombre){
      return repositorioTipoJornadaLaboral.encontrarJornadaLaboralPorNombre(nombre);
   }

   public void inicializarJornadas(){
           TipoJornadaLaboral tj1 = new TipoJornadaLaboral(1L, "Part Time");
           TipoJornadaLaboral tj2 = new TipoJornadaLaboral(2L, "Full Time");
           TipoJornadaLaboral tj3 = new TipoJornadaLaboral(3L, "Horas extra");
           TipoJornadaLaboral tj4 = new TipoJornadaLaboral(4L, "Vacaciones");
           TipoJornadaLaboral tj5 = new TipoJornadaLaboral(5L, "Dia libre");
           repositorioTipoJornadaLaboral.save(tj1);
           repositorioTipoJornadaLaboral.save(tj2);
           repositorioTipoJornadaLaboral.save(tj3);
           repositorioTipoJornadaLaboral.save(tj4);
           repositorioTipoJornadaLaboral.save(tj5);
   }
}

