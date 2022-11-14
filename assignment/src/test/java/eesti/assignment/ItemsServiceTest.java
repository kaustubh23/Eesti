package eesti.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import eesti.assignment.model.Itemdetails;
import eesti.assignment.service.ItemsService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ItemsServiceTest {
	@Autowired
	ItemsService service;

	@Test
	public void save() {

		Random ramdom = new Random();
		int upperbound = 80;
		int int_random = ramdom.nextInt(upperbound);
		Itemdetails item = Itemdetails.builder().id(80).name("Pastry").price(4).type("1").quantity(12).build();
		Mono<Itemdetails> savedItem = service.saveItem(item);
		Itemdetails saved = savedItem.block();

		// when(service.getItemsById(int_random)).thenReturn(Mono.just(item));
		Itemdetails userMono = service.getItemsById(80).block();
		assertEquals(item.getName(), userMono.getName());

	}

	
	@Test
	public void getById() {
		Mono<Itemdetails> saved = service.getItemsById(1);
		assertEquals("Brownie", saved.block().getName());

	}
	
	
	@Test
	public void updateItemDetails() {
		
		List<Itemdetails> values = new ArrayList<Itemdetails>();
		Mono<Itemdetails> one = service.getItemsById(1);
		Mono<Itemdetails> two = service.getItemsById(2);
		Itemdetails item1=	one.block();
		item1.setQuantity(90);
		Itemdetails item2=	two.block();
		item2.setQuantity(80);
		values.add(item1);
		values.add(item2);
		
		Flux<Itemdetails> saved = service.buyItems(values);
		List<Itemdetails> savedValues= saved.collectList().block();
		assertEquals(90, savedValues.get(0).getQuantity());
		assertEquals(80, savedValues.get(1).getQuantity());
	}
	
	
	
}
