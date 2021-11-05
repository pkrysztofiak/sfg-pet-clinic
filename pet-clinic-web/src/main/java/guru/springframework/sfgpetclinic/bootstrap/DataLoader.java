package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");

        Specialty savedRadiology = specialtyService.save(radiology);
        Specialty savedSurgery = specialtyService.save(surgery);
        Specialty savedDentistry = specialtyService.save(dentistry);

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

        Visit meowVisit = new Visit();
        meowVisit.setPet(meow);
        meowVisit.setDate(LocalDate.now());
        meowVisit.setDescription("Sneezy kitty");

        visitService.save(meowVisit);

        System.out.println("Owners initialized");

        Vet samAxe = new Vet();
        samAxe.setFirstName("Sam");
        samAxe.setLastName("Axe");
        samAxe.getSpecialties().add(savedRadiology);
        vetService.save(samAxe);

        Vet jessiePorter = new Vet();
        jessiePorter.setFirstName("Jessie");
        jessiePorter.setLastName("Porter");
        jessiePorter.getSpecialties().add(savedSurgery);

        vetService.save(jessiePorter);

        System.out.println("Vets initialized");
    }
}
