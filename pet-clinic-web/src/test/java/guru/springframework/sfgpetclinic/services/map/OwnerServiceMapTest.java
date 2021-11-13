package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;

    final Long ownerId = 1l;
    final String lastName = "Smith";

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeMapService(), new PetServiceMap());
        ownerServiceMap.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerServiceMap.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(ownerId);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void saveExistingId() {
        Long id = 2l;
        Owner owner = ownerServiceMap.save(Owner.builder().id(id).build());
        assertEquals(id, owner.getId());
    }

    @Test
    void saveNonExistingId() {
        Owner owner = ownerServiceMap.save(Owner.builder().build());
        assertNotNull(owner);
        assertNotNull(owner.getId());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerId));
        Set<Owner> owners = ownerServiceMap.findAll();
        assertEquals(0, owners.size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerId);
        Set<Owner> owners = ownerServiceMap.findAll();
        assertEquals(0, owners.size());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerServiceMap.findByLastName("Smith");
        assertNotNull(owner);
        assertEquals(lastName, owner.getLastName());
    }

    @Test
    void findByLastNameNotFound() {
        Owner owner = ownerServiceMap.findByLastName("Foo");
        assertNull(owner);
    }
}