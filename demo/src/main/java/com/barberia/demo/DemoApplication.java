package com.barberia.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.barberia.demo.dtos.UsuarioDTO;
import com.barberia.demo.entidades.administradorEntidad;
import com.barberia.demo.entidades.jefeEntidad;
import com.barberia.demo.entidades.usuarioEntidad;
import com.barberia.demo.enums.Rol;
import com.barberia.demo.utilidades.ConversorEntidad_Dto;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		pruebaConvEntidadDto();
	}

	private static void pruebaConvEntidadDto() {
		usuarioEntidad usuario = new usuarioEntidad();
		usuario.setNombre("Nahuel");
		usuario.setEmail("nahuelg8799@gmail.com");
		usuario.setTelefono("3425553930");

		administradorEntidad admin = new administradorEntidad();
		admin.setRol(Rol.ADMIN);
		
		jefeEntidad jefe = new jefeEntidad();
		jefe.setRol(Rol.JEFE);
		
		usuario.setAdmin(admin);
		admin.setUsuarioAdmin(usuario);
		usuario.setJefe(jefe);
		jefe.setUsuarioJefe(usuario);

		UsuarioDTO usuarioDTO = ConversorEntidad_Dto.convertirUsuario(usuario);
	}
}
