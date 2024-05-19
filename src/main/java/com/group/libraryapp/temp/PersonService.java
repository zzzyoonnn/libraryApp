package com.group.libraryapp.temp;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {

    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;

    public PersonService(AddressRepository addressRepository, PersonRepository personRepository) {
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
    }

    @Transactional
    public void savePerson() {
        Person person = personRepository.save(new Person());
        Address address = addressRepository.save(new Address());
        person.setAddress(address);     // 이 코드를 실행하면 DB에서 정상으로 테이블이 연결된다.
        address.getPerson();
    }
}
