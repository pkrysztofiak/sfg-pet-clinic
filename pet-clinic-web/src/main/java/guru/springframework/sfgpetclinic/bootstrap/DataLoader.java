package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner michaelWeston = new Owner();
        michaelWeston.setFirstName("Michael");
        michaelWeston.setLastName("Weston");
        michaelWeston.setAddress("123 Brickerel");
        michaelWeston.setCity("Miami");
        michaelWeston.setTelephone("12343948");

        Pet rosco = new Pet();
        rosco.setName("Rosco");
        rosco.setPetType(savedDogPetType);
        rosco.setOwner(michaelWeston);
        rosco.setBirthDate(LocalDate.now());
        ownerService.save(michaelWeston);

        Owner fionaGlenanne = new Owner();
        fionaGlenanne.setFirstName("Fiona");
        fionaGlenanne.setLastName("Glenanne");
        fionaGlenanne.setAddress("321 Alvaro");
        fionaGlenanne.setCity("Denver");
        fionaGlenanne.setTelephone("7853893983");

        Pet meow = new Pet();
        meow.setName("Meow");
        meow.setPetType(savedCatPetType);
        meow.setOwner(fionaGlenanne);
        meow.setBirthDate(LocalDate.now());
        fionaGlenanne.getPets().add(meow);

        ownerService.save(fionaGlenanne);

        System.out.println("Owners initialized");

        Vet samAxe = new Vet();
        samAxe.setFirstName("Sam");
        samAxe.setLastName("Axe");
        vetService.save(samAxe);

        Vet jessiePorter = new Vet();
        jessiePorter.setFirstName("Jessie");
        jessiePorter.setLastName("Porter");

        vetService.save(jessiePorter);

        System.out.println("Vets initialized");
    }
}
