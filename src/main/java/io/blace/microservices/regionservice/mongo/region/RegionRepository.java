package io.blace.microservices.regionservice.mongo.region;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegionRepository extends MongoRepository<Region, String> {

	Region findById(String id);
	
	List<Region> findAll();
	
}
