package eesti.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eesti.assignment.model.Itemdetails;
import eesti.assignment.service.ItemsService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/items")
public class ItemsController {

	ItemsService service;

	@Autowired
	public ItemsController(ItemsService service) {
		this.service = service;
	}

	@GetMapping
	public Flux<Itemdetails> getItems() {
		return service.getItems();
	}

	@GetMapping("/{id}")
	public Mono<Itemdetails> getItemsById(@PathVariable Integer id) {
		return service.getItemsById(id);
	}

	@PostMapping("/{save}")
	public Mono<Itemdetails> saveItem(@RequestBody Itemdetails items) {
		return service.saveItem(items);
	}

	@SuppressWarnings("rawtypes")
	@PutMapping("/{buy}")
	public Flux<Itemdetails> buyItem(@RequestBody List<Itemdetails> items) {

		return service.buyItem2(items);
	}

}
