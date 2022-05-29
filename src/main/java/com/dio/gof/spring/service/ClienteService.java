package com.dio.gof.spring.service;

import com.dio.gof.spring.model.Cliente;

/**
 * ClienteService
 */

public interface ClienteService {
  Iterable<Cliente> queryAll();

  Cliente queryById(Long id);

  void insert(Cliente cliente);

  void update(Long id, Cliente cliente);

  void delete(Long id);
}
