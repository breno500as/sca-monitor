package com.puc.sca.monitor.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.puc.sca.monitor.model.DadoSensorBarragem;

@FeignClient(name = "sca-bi" , path = "/bi/dados-monitoramento")
public interface DadoMonitoramentoClient {
	
	@PostMapping
	public void post(DadoSensorBarragem dadoSensor);

}
