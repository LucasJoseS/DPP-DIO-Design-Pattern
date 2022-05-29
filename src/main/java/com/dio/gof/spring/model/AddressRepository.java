package com.dio.gof.spring.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * AddressRepository
 */
@Repository
public interface AddressRepository extends CrudRepository<Address, String> {

}
