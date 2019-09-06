package com.puc.sca.monitor.controller;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.puc.sca.integration.util.Alerta;
import com.puc.sca.integration.util.NivelAlerta;
import com.puc.sca.monitor.model.DadoSensorBarragem;
import com.puc.sca.monitor.service.ModuloAlertaService;

/**
 * Controller integrado a sensores IOT instalados na barragem informando os
 * níveis de alerta.
 * 
 * @author breno
 *
 */
@RestController
@RequestMapping("dados-sensor")
public class DadoSensorBarragemController {

	@Autowired
	private ModuloAlertaService moduloAlertaService;

	@Autowired
	private EurekaClient discoveryClient;

	@PostMapping
	public void recebeDadosSensor(@RequestBody DadoSensorBarragem dadoSensorBarragem) {

		if (NivelAlerta.NIVEL_4_ROMPIMENTO_IMINENTE.equals(dadoSensorBarragem.getNivelAlerta())) {
			// Tópico exclusivo para acionar sirenes, alarmes e emails.
			this.moduloAlertaService
					.acionaModuloSegurancaComunicacaoEvacuacao(new Alerta(dadoSensorBarragem.getNivelAlerta()));
			this.moduloAlertaService.acionaModuloSegurancaComunicacaoSitemaCorpoDeBombeiros(
					new Alerta(dadoSensorBarragem.getNivelAlerta()));
			this.moduloAlertaService
					.acionaModuloSegurancaIntegracaoSistemaDefesaCivil(new Alerta(dadoSensorBarragem.getNivelAlerta()));
		}

		this.enviaDadoSensorMicroservicoBI(dadoSensorBarragem);
	}

	private void enviaDadoSensorMicroservicoBI(DadoSensorBarragem dadoSensorBarragem) {
		try {

			final ObjectMapper objectMapper = new ObjectMapper();
			final HttpClient httpClient = HttpClients.createDefault();
			String json = objectMapper.writeValueAsString(dadoSensorBarragem);

			final HttpPost httpPost = new HttpPost(this.getServiceUrl());
			StringEntity entity = new StringEntity(json);
			httpPost.setEntity(entity);
			httpPost.setHeader("Content-type", "application/json");
			httpPost.setEntity(entity);
			httpClient.execute(httpPost);
		} catch (Exception e) {
			// TODO: logar e tratar exceção
			e.printStackTrace();
		}

	}

	private String getServiceUrl() {
		InstanceInfo instance = discoveryClient.getNextServerFromEureka("sca-bi", false);
		return instance.getHomePageUrl() + "bi/dados-monitoramento";
	} 

}
