package eesti.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eesti.assignment.model.Itemdetails;
import eesti.assignment.repository.ItemsRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/items")
public class ItemsController {

	@Autowired
	ItemsRepository repository;

	@GetMapping
	public Flux<Itemdetails> getItems() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public Mono<Itemdetails> getItemsById(@PathVariable Integer id) {
		return repository.findById(id);
	}

	@PostMapping
	public Mono<Itemdetails> saveItem(@RequestBody Itemdetails items) {
		return repository.save(items);
	}
	

	/*
	 * @SuppressWarnings("rawtypes")
	 * 
	 * @PutMapping("/{id}") public Mono<ResponseEntity> updateItem(@RequestBody
	 * Itemdetails items, @PathVariable Integer id) { return
	 * repository.findById(id).map((c) -> { c.setName(items.getName()); return c;
	 * }).flatMap(c -> Mono.just(ResponseEntity.ok(c))) .cast(ResponseEntity.class)
	 * .onErrorResume(ex -> { ex.printStackTrace(); return
	 * Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage())
	 * ); }); }
	 */
	
	
	
	@SuppressWarnings("rawtypes")
	@PutMapping("/{buy}")
	public Mono<ResponseEntity> buyItem(@RequestBody Itemdetails items) {
		return repository.findById(items.getId()).map((c) -> {
			c.setQuantity(items.getQuantity());
			return c;
		}).flatMap(c -> Mono.just(ResponseEntity.ok(c)))
        .cast(ResponseEntity.class)
        .onErrorResume(ex -> {
            ex.printStackTrace();
            return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()));
        });		
		
		
		
		
		
		
		
		
		
	}

}
