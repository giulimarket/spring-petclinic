package guru.springframework.sfgpetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.VisitService;

@Component
public class DataLoader implements CommandLineRunner {
	
	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	private final SpecialityService specialityService;
	private final VisitService visitService;
	
	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.specialityService = specialityService;
		this.visitService = visitService;
	}

	@Override
	public void run(String... args) throws Exception {
		int count = petTypeService.findAll().size();
		
		if(count == 0) {
			loadData();
		}
		
	}

	private void loadData() {
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType savedDogPetType =petTypeService.save(dog);
		
		PetType cat = new PetType();
		cat.setName("Cat");
		PetType savedCatPetType =petTypeService.save(cat);
		
		Speciality radiology = new Speciality();
		radiology.setDescription("Radiology");
		specialityService.save(radiology);
		
		Speciality surgery = new Speciality();
		surgery.setDescription("Surgery");
		specialityService.save(surgery);
		
		Speciality dentistry = new Speciality();
		dentistry.setDescription("Dentistry");
		specialityService.save(dentistry);
		
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
		
		Pet giulyCat = new Pet();
		giulyCat.setPetType(savedCatPetType);
		giulyCat.setOwner(owner2);
		giulyCat.setBirthDate(LocalDate.now());
		giulyCat.setName("Ciro");
		giulyCat.setOwner(owner2);
		owner2.getPets().add(giulyCat);
		
		ownerService.save(owner2);
		
		Visit catVisit = new Visit();
		catVisit.setPet(giulyCat);
		catVisit.setDate(LocalDate.now());
		catVisit.setDescription("Gatto molto malato");
		
		visitService.save(catVisit);
		
		System.out.println("Loaded Owners");
		
		Vet vet1 = new Vet();
		vet1.setFirstName("Michael");
		vet1.setLastName("Wetson");	
		vet1.getSpecialties().add(dentistry);
		vetService.save(vet1);
		
		Vet vet2 = new Vet();
		vet2.setFirstName("Michael2");
		vet2.setLastName("Wetson2");
		vet1.getSpecialties().add(surgery);
		vetService.save(vet2);
		
		System.out.println("Loaded Vets");
	}


}
