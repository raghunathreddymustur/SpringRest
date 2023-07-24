package com.raghu.rest.intro.dao;

import com.raghu.rest.intro.pojo.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressesDao extends CrudRepository<Address, Integer> {
}
