package com.puc.sca.monitor.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puc.sca.monitor.service.ModuloAlertaService;
import com.puc.sca.util.pojo.Alerta;
import com.puc.sca.util.pojo.Constants;
import com.puc.sca.util.pojo.NivelAlerta;


/**
 * Rest controller de comunicação entre esse microserviço de monitoramento e o microserviço do módulo de segurança e informação JMS, passando 
 * informações para sistemas terceiros integrados.
 * @author breno
 *
 */

@RestController
@RequestMapping("comunicacoes-seguranca")
public class ComunicaoSegurancaBarragemController {
	
	@Autowired
	private ModuloAlertaService moduloAlertaService;


	@PostMapping
	public ResponseEntity<?> informaSistemasIntegrados(@RequestBody Alerta alerta, HttpServletRequest request) {
		
		alerta.setNomeUsuarioLogado(request.getParameter(Constants.NOME_USUARIO_LOGADO));
		alerta.setEmailUsuarioLogado(request.getParameter(Constants.EMAIL_USUARIO_LOGADO));
		
		// Tópico exclusivo para acionar sirenes, alarmes e emails.
		if (NivelAlerta.ROMPIMENTO_IMINENTE.equals(alerta.getNivel())) {
			this.moduloAlertaService.acionaModuloSegurancaComunicacaoEvacuacao(alerta);
		}
		
		
		this.moduloAlertaService.acionaModuloSegurancaComunicacaoSitemaCorpoDeBombeiros(alerta);
		this.moduloAlertaService.acionaModuloSegurancaIntegracaoSistemaDefesaCivil(alerta);
	 
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

 

}
