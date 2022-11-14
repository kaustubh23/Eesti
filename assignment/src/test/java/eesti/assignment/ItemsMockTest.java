package eesti.assignment;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import eesti.assignment.controller.ItemsController;
import eesti.assignment.model.Itemdetails;
import eesti.assignment.repository.ItemsRepository;
import eesti.assignment.service.ItemsService;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ItemsController.class)
@Import(ItemsService.class)
@ActiveProfiles("test")
public class ItemsMockTest {
	@MockBean
	ItemsRepository repository;
	@InjectMocks
	private ItemsService userService;

	@Test
	void testGetEmployeeById() {
		Itemdetails item = new Itemdetails();
		item.setId(12);
		item.setName("Fatcake");
		item.setPrice(12);
		item.setType("1");
		item.setQuantity(60);
		Mockito.when(repository.findById(12)).thenReturn(Mono.just(item));
		Mono<Itemdetails> itemd = repository.findById(12);
		itemd.block();
		Mockito.verify(repository, times(1)).findById(12);
	}
	
	
	@Test
	void testSaveANewItem() {
		Itemdetails item = new Itemdetails();
		item.setId(120);
		item.setName("IceCream");
		item.setPrice(1.0);
		item.setType("1");
		item.setQuantity(60);
		Mockito.when(repository.save(item)).thenReturn(Mono.just(item));
		Mono<Itemdetails> itemd = repository.save(item);
		Itemdetails value=itemd.block();
		Mockito.verify(repository, times(1)).save(item);
		  assertEquals("IceCream", value.getName());
	
	}
	


}