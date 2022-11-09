package eesti.assignment.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import eesti.assignment.model.Itemdetails;

public interface ItemsRepository extends ReactiveCrudRepository<Itemdetails,Integer> {

}
