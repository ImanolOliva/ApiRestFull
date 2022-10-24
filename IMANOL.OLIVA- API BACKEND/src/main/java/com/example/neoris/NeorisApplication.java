package com.example.neoris;

import com.example.neoris.Model.TipoJornadaLaboral;
import com.example.neoris.Repository.RepositorioJornada;
import com.example.neoris.Repository.RepositorioTipoJornadaLaboral;
import com.example.neoris.Service.ServicioTipoJornadaLaboral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@SpringBootApplication
public class  NeorisApplication {

	public static void main(String[] args) {
	SpringApplication.run(NeorisApplication.class, args);

	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("*").allowedHeaders("*");
			}
		};
	}

}





