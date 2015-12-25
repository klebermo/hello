package org.hello.generic.controller;

import org.hello.generic.service.basicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class basicController<E> {
	
	@Autowired
	protected basicService<E> serv;

	private Class<E> clazz;
	
	public basicController(Class<E> clazz) {
		this.setClazz(clazz);
	}

	public Class<E> getClazz() {
		return clazz;
	}

	public void setClazz(Class<E> clazz) {
		this.clazz = clazz;
	}
	
	public String getName() {
		return clazz.getSimpleName();
	}
	
	@RequestMapping("cadastra")
	public ModelAndView insert() {
		return new ModelAndView("form", "command", null);
	}
	
	@RequestMapping(value="cadastra", method=RequestMethod.POST)
	@ResponseBody
	public void insert(@ModelAttribute("object") E object, BindingResult result) {
		serv.insert(object);
	}
	
	@RequestMapping("atualiza/{id}")
	public ModelAndView update(@PathVariable("id") String id) {
		return new ModelAndView("form", "command", serv.select(id));
	}
	
	@RequestMapping(value="atualiza", method=RequestMethod.POST)
	@ResponseBody
	public void update(@ModelAttribute("object") E object, BindingResult result) {
		serv.update(object);
	}
	
	@RequestMapping("remove/{id}")
	public ModelAndView delete(@PathVariable("id") String id) {
		return new ModelAndView("confirm_remove", "command", serv.select(id));
	}
	
	@RequestMapping(value="remove", method=RequestMethod.POST)
	@ResponseBody
	public void delete(@ModelAttribute("object") E object, BindingResult result) {
		serv.delete(object);
	}
	
	@RequestMapping("listagem")
	public ModelAndView select() {
		return new ModelAndView("listagem");
	}
	
	@RequestMapping("listagem.json")
	@ResponseBody
	public String select2() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(serv.select());
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    //binder.registerCustomEditor(Pagina.class, new PaginaEditor());
	}
	
}
