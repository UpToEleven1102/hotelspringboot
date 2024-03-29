package com.example.ec.explorecalifornia;

import com.example.ec.explorecalifornia.domain.Difficulty;
import com.example.ec.explorecalifornia.domain.Region;
import com.example.ec.explorecalifornia.service.TourPackageService;
import com.example.ec.explorecalifornia.service.TourService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

import static com.example.ec.explorecalifornia.ExplorecaliforniaApplication.TourFromFile.importTours;

@SpringBootApplication
public class ExplorecaliforniaApplication implements CommandLineRunner {
	@Autowired
	private TourService tourService;

	@Autowired
	private TourPackageService tourPackageService;

	public static void main(String[] args) {
		SpringApplication.run(ExplorecaliforniaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		tourPackageService.createTourPackage("BC", "Backpack Cal");
		tourPackageService.createTourPackage("CC", "California Calm");
		tourPackageService.createTourPackage("CH", "California Hot springs");
		tourPackageService.createTourPackage("CY", "Cycle California");
		tourPackageService.createTourPackage("DS", "From Desert to Sea");
		tourPackageService.createTourPackage("KC", "Kids California");
		tourPackageService.createTourPackage("NW", "Nature Watch");
		tourPackageService.createTourPackage("SC", "Snowboard Cali");
		tourPackageService.createTourPackage("TC", "Taste of California");

		tourPackageService.lookUp().forEach(t-> System.out.println(t));

		importTours().forEach(t-> tourService.createTour(t.title,t.description,t.blurb,Integer.parseInt(t.price),t.length,t.bullets,t.keywords,t.packageType, Difficulty.valueOf(t.difficulty), Region.findByLabel(t.region)));

		tourService.loopUp().forEach(t-> System.out.println(t));

	}

	static class  TourFromFile{

		private String packageType, title, description, blurb, price, length, bullets, keywords,  difficulty, region;

		static List<TourFromFile> importTours() throws IOException{
			return new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).
					readValue(TourFromFile.class.getResourceAsStream("/ExploreCalifornia.json"),new TypeReference<List<TourFromFile>>(){});
		}
	}
}
