package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.map.OwnerServiceMap;
import guru.springframework.sfgpetclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner michaelWeston = new Owner();
        michaelWeston.setId(1L);
        michaelWeston.setFirstName("Michael");
        michaelWeston.setLastName("Weston");
        ownerService.save(michaelWeston);

        Owner fionaGlenanne = new Owner();
        fionaGlenanne.setId(2L);
        fionaGlenanne.setFirstName("Fiona");
        fionaGlenanne.setLastName("Glenanne");
        ownerService.save(fionaGlenanne);

        System.out.println("Owners initialized");

        Vet samAxe = new Vet();
        samAxe.setId(1L);
        samAxe.setFirstName("Sam");
        samAxe.setLastName("Axe");
        vetService.save(samAxe);

        Vet jessiePorter = new Vet();
        jessiePorter.setId(2L);
        jessiePorter.setFirstName("Jessie");
        jessiePorter.setLastName("Porter");

        vetService.save(jessiePorter);

        System.out.println("Vets initialized");
    }
}
