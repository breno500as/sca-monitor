package com.puc.sca.monitor.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.puc.sca.monitor.model.DadoSensorBarragem;

public interface DadoSensorBarragemRepository extends PagingAndSortingRepository<DadoSensorBarragem, String> {

}
