package guru.springframework.sfgpetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.map.OwnerServiceMap;
import guru.springframework.sfgpetclinic.services.map.VetServiceMap;

@Component
public class DataLoader implements CommandLineRunner {
	
	private final OwnerService ownerService;
	private final VetService vetService;
	//private final PetService petService;
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
		PetType savedDogPetType =petTypeService.save(dog);
		
		PetType cat = new PetType();
		cat.setName("Cat");
		PetType savedCatPetType =petTypeService.save(cat);
		
		Owner owner1 = new Owner();
		owner1.setFirstName("Michael");
		owner1.setLastName("Wetson");
		owner1.setAddress("via lisbo 6");
		owner1.setCity("Padova");
		owner1.setTelephone("3233333332");
		ownerService.save(owner1);
		
		Pet mikesPet = new Pet();
		mikesPet.setPetType(savedDogPetType);
		mikesPet.setOwner(owner1);
		mikesPet.setBirthDate(LocalDate.now());
		mikesPet.setName("Rosco");
		owner1.getPets().add(mikesPet);
		
		Owner owner2 = new Owner();
		owner2.setFirstName("Giulian");
		owner2.setLastName("Market");
		owner2.setAddress("via lisbo 6");
		owner2.setCity("Padova");
		owner2.setTelephone("3233434432");
		ownerService.save(owner2);
		
		Pet giulyCat = new Pet();
		giulyCat.setPetType(savedCatPetType);
		giulyCat.setOwner(owner2);
		giulyCat.setBirthDate(LocalDate.now());
		giulyCat.setName("Ciro");
		owner2.getPets().add(giulyCat);
		
		System.out.println("Loaded Owners");
		
		Vet vet1 = new Vet();
		vet1.setFirstName("Michael");
		vet1.setLastName("Wetson");		
		vetService.save(vet1);
		
		Vet vet2 = new Vet();
		vet2.setFirstName("Michael2");
		vet2.setLastName("Wetson2");		
		vetService.save(vet2);
		
		System.out.println("Loaded Vets");
	}


}
