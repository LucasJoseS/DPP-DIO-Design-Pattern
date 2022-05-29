package com.dio.gof.spring.service;

import com.dio.gof.spring.model.Address;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ViaCepService
 */
@FeignClient(name = "viaCep", url = "https://viacep.com.br/ws")
public interface ViaCepService {
  @GetMapping("{cep}/json/")
  Address consutarCep(@PathVariable("cep") String cep);
}
