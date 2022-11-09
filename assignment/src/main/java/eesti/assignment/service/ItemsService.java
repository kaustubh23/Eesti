package eesti.assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eesti.assignment.model.Itemdetails;
import eesti.assignment.repository.ItemsRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ItemsService {
	@Autowired
	ItemsRepository repository;


	public Flux<Itemdetails> getItems() {
		return repository.findAll();
	}

	public Mono<Itemdetails> getItemsById(Integer id) {
		return repository.findById(id);
	}



	@Transactional
	public Mono<Itemdetails> saveItem(Itemdetails items) {
		return this.repository.findById(items.getId()).flatMap(p -> {
			p.setName(items.getName());
			p.setPrice(items.getPrice());
			p.setType(items.getType());
			p.setQuantity(items.getQuantity());
			p.setEntry(items.isEntry());
			return this.repository.save(p);
		}).switchIfEmpty(this.repository.save(items.setAsNew()));
	}

	@SuppressWarnings("unchecked")
	public Mono<Itemdetails> buyItem(Itemdetails value) {
		return this.repository.findById(value.getId()).flatMap(p -> {
			if (p.getQuantity() - value.getQuantity() >= 0) {
				p.setName(value.getName());
				p.setPrice(value.getPrice());
				p.setType(value.getType());
				p.setQuantity(p.getQuantity() - value.getQuantity());
				p.setEntry(value.isEntry());
				this.repository.save(p);
			} else {
				return Mono.empty();
			}
			return Mono.just(value);

		});

	}

	@SuppressWarnings("unchecked")
	public Flux<Itemdetails> buyItem2(List<Itemdetails> value) {
		return repository.saveAll(value);
		
		}
}
