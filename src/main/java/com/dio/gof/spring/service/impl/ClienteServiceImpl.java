package com.dio.gof.spring.service.impl;

import java.util.Optional;

import com.dio.gof.spring.model.Address;
import com.dio.gof.spring.model.AddressRepository;
import com.dio.gof.spring.model.Cliente;
import com.dio.gof.spring.model.ClienteRepository;
import com.dio.gof.spring.service.ClienteService;
import com.dio.gof.spring.service.ViaCepService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClienteServiceImpl
 */
@Service
public class ClienteServiceImpl implements ClienteService {
  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private AddressRepository addressRepository;

  @Autowired
  private ViaCepService viaCepService;

  @Override
  public Iterable<Cliente> queryAll() {
    return clienteRepository.findAll();
  }

  @Override
  public Cliente queryById(Long id) {
    Optional<Cliente> cliente = clienteRepository.findById(id);
    return cliente.get();
  }

  @Override
  public void insert(Cliente cliente) {
    saveClienteWithCep(cliente);
  }

  @Override
  public void update(Long id, Cliente cliente) {
    Optional<Cliente> old = clienteRepository.findById(id);

    if (old.isPresent()) {
      saveClienteWithCep(cliente);
    }
  }

  @Override
  public void delete(Long id) {
    clienteRepository.deleteById(id);
  }

  private void saveClienteWithCep(Cliente cliente) {
    String cep = cliente.getAddress().getCep();

    Address address = addressRepository.findById(cep).orElseGet(() -> {
      Address newAddress = viaCepService.consutarCep(cep);
      addressRepository.save(newAddress);

      return newAddress;
    });

    cliente.setAddress(address);
    clienteRepository.save(cliente);
  }
}
