package br.com.vanessaeich.services;

import br.com.vanessaeich.dtos.v1.PersonDTO;
import br.com.vanessaeich.dtos.v2.PersonDTOV2;
import br.com.vanessaeich.exceptions.ResourceNotFoundException;
import br.com.vanessaeich.mapper.DozerMapper;
import br.com.vanessaeich.mapper.PersonMapperV2;
import br.com.vanessaeich.model.Person;
import br.com.vanessaeich.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author Vanessa Eich on 10/01/2023
 */
@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapperV2 mapperV2;

    public List<PersonDTO> findAll(){

        logger.info("Log- Finding all people");

        return DozerMapper.parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id){

        logger.info("Log- Finding one person");

        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        return DozerMapper.parseObject(entity, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person) {
        logger.info("Log- Creating one person");
        Person entity = DozerMapper.parseObject(person, Person.class);
        PersonDTO dto = DozerMapper.parseObject(repository.save(entity), PersonDTO.class);

        return dto;
    }

    public PersonDTOV2 createV2(PersonDTOV2 person) {
        logger.info("Log- Creating one person V2");
        Person entity = mapperV2.converDtoToEntity(person);
        PersonDTOV2 dto = mapperV2.convertEntityToDto(repository.save(entity));

        return dto;
    }
    public PersonDTO update(PersonDTO person) {
        logger.info("Log- Updating one person");
        Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        PersonDTO dto = DozerMapper.parseObject(repository.save(entity), PersonDTO.class);
        return dto;
    }

    public void delete(Long id) {

        logger.info("Log- Deleting one person");

        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        repository.delete(entity);
    }

}
