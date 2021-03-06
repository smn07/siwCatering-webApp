package com.example.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.Buffet;
import com.example.demo.model.Chef;
import com.example.demo.service.ChefService;

@Component
public class ChefValidator implements Validator{
	
	@Autowired
	private ChefService chefService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Chef.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Chef chef = (Chef)o;
		
		if(this.chefService.alreadyExists((Chef)o)) {
			errors.reject("chef.duplicato");
		}
		
		if(chef.getNome().isEmpty()) {
			errors.reject("NotBlank.chef.nome");
		}
		
		if(chef.getCognome().isEmpty()) {
			errors.reject("NotBlank.chef.cognome");
		}
		
		if(chef.getNazionalita().isEmpty()) {
			errors.reject("NotBlank.chef.nazionalita");
		}
	}

	
}
