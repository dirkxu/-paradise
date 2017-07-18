package hello.mappers;

import hello.domain.Address;

public interface AddressMapper {
  Address findAddressById(Integer id);
}
