package org.hello.model.usuario;

import org.hello.generic.controller.basicController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("usuario")
public class UsuarioController extends basicController<Usuario> {

	public UsuarioController() {
		super(Usuario.class);
	}

}
