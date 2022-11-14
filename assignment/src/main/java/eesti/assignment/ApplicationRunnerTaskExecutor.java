package eesti.assignment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import eesti.assignment.model.Itemdetails;
import eesti.assignment.service.ItemsService;
import reactor.core.publisher.Mono;

@Profile("!test")
@Component
public class ApplicationRunnerTaskExecutor implements ApplicationRunner{

	@Autowired
	ItemsService itemsService;

	public List<Itemdetails> getItems() {
		List<Itemdetails> list = new ArrayList<Itemdetails>();

		list.add(new Itemdetails(1, "Brownie", 0.65, "1", 45, false));
		list.add(new Itemdetails(2, "Muffin", 1.00, "1", 36, false));
		list.add(new Itemdetails(3, "Cake Pop", 1.35, "1", 24, false));
		list.add(new Itemdetails(4, "Apple tart", 1.50, "1", 60, false));
		list.add(new Itemdetails(5, "Water", 1.50, "1", 30, false));
		list.add(new Itemdetails(6, "Shirt", 2.00, "2", 6, false));
		list.add(new Itemdetails(7, "Pants", 3.00, "2", 4, false));
		list.add(new Itemdetails(8, "Jacket", 4.00, "2", 6, false));
		list.add(new Itemdetails(9, "Toy", 1.00, "2", 3, false));
		return list;
	}

    @Profile("!test")
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		for (Itemdetails item : getItems()) {
			Mono<Itemdetails> mono = itemsService.getItemsById(item.getId());
			Itemdetails value = mono.block();
			if (value == null) {
				itemsService.saveItem(item.setAsNew()).subscribe(result -> System.out.print(result));
				;
			}

		}
	}
}
