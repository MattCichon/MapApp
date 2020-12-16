package com.matt.MapApp;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.Random;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MapController {
	@Autowired
	private MapService mapService;
	
	
	@GetMapping({"/home", "/"})
	public String getDefaultMap(Model model) {
		model.addAttribute("location", new Location());
		return "index.html";
	}
	
	@PostMapping("/home")
	public String getMapForLocation(Location location, Model model) {
		mapService.addCoordinates(location);
		model.addAttribute("location", location);
		return "index";
	}
	
	@PostMapping("random")
	public String getMapForRandomLocation(Model model) {
		Location randLocation = mapService.randomizeCoords();
		model.addAttribute("location", randLocation);
		
		
		return "index";
	}
}
