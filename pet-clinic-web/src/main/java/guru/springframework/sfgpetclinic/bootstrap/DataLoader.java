package guru.springframework.sfgpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.Owner;
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
		
		// TODO Auto-generated method stub
		Owner owner1 = new Owner();
		//owner1.setId(1L);
		owner1.setFirstName("Michael");
		owner1.setLastName("Wetson");		
		ownerService.save(owner1);
		
		Owner owner2 = new Owner();
		//owner2.setId(2L);
		owner2.setFirstName("Giulian");
		owner2.setLastName("Market");		
		ownerService.save(owner2);
		
		System.out.println("Loaded Owners");
		
		Vet vet1 = new Vet();
		//vet1.setId(1L);
		vet1.setFirstName("Michael");
		vet1.setLastName("Wetson");		
		vetService.save(vet1);
		
		Vet vet2 = new Vet();
		//vet2.setId(2L);
		vet2.setFirstName("Michael2");
		vet2.setLastName("Wetson2");		
		vetService.save(vet2);
		
		System.out.println("Loaded Vets");
	}


}
