package com.example.neoris.Controller;


import com.example.neoris.Model.TipoJornadaLaboral;
import com.example.neoris.Service.ServicioTipoJornadaLaboral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ControllerTipoJornadaLaboral {


    @Autowired
    ServicioTipoJornadaLaboral serviceTipoJornadaLaboral;

    @PostMapping("/guardarTiposJornadaLaboral")
    public TipoJornadaLaboral guardarTipoJornadaLaboral(@RequestBody TipoJornadaLaboral tipoJornadaLaboral){
       TipoJornadaLaboral tipo = serviceTipoJornadaLaboral.guarTipoJornadaLaboral(tipoJornadaLaboral);
       return tipo;
    }

    @GetMapping("/listarTiposDeJornadaLaboral")
    public List<TipoJornadaLaboral> listarTipoJornadaLaboral(TipoJornadaLaboral tipoJornadaLaboral){
        List<TipoJornadaLaboral> tipo =  serviceTipoJornadaLaboral.listarTipoJornadaLaboral(tipoJornadaLaboral);
        return tipo;
    }


    @GetMapping("/listarTiposDeJornadaLaboral/{id}")
    public TipoJornadaLaboral listarTipoJornadaLaboralPorId(@PathVariable("id") Long id){
        TipoJornadaLaboral tipo = serviceTipoJornadaLaboral.listarTipoJornadaLaboralPorId(id);
        return tipo;
    }
}
