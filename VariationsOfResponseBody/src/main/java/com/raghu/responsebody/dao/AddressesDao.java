package com.raghu.responsebody.dao;

import com.raghu.responsebody.ds.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressesDao extends CrudRepository<Address, Integer> {
}
