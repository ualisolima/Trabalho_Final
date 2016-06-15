package br.ufc.trabalho_final;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import br.ufc.trabalho_final.interceptor.AutorizadorInteceptor;


@Configuration
public class TrabalhoFinalConfig extends WebMvcConfigurerAdapter{

	@Autowired
	private AutorizadorInteceptor autorizadorInteceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(autorizadorInteceptor);
	}
}
