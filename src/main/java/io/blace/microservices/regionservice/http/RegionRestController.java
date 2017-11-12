package io.blace.microservices.regionservice.http;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.blace.microservices.regionservice.mongo.region.Region;
import io.blace.microservices.regionservice.mongo.region.RegionRepository;

@RestController
public class RegionRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RegionRepository regionrepo;
	
    @CrossOrigin
    @RequestMapping(value = "region")
    public ResponseEntity<List<Region>> getallregions() {
    		logger.info("getallregions requested");
        return new ResponseEntity<List<Region>>(regionrepo.findAll(), HttpStatus.OK);
    }
    
    @CrossOrigin
    @RequestMapping(value = "region", params = {"id"})
    public ResponseEntity<Region> getbyid(@RequestParam("id") String id){
    		logger.info("getbyid requested for " +  id);
        return new ResponseEntity<Region>(regionrepo.findById(id), HttpStatus.OK);
    }   
    
    @CrossOrigin
    @PostMapping("/region")
    public ResponseEntity<Region> createregion(@RequestBody Region region) {
    		logger.info("createregion requested for " +  region.toString());
    		regionrepo.save(region);
        return new ResponseEntity<Region>(HttpStatus.CREATED);
    }
    
    @CrossOrigin
    @PostMapping("/regions")
    public ResponseEntity<Region> createregions(@RequestBody List<Region> regions) {
    		logger.info("createregions requested");
    		
    		for( Region region : regions) {
    			regionrepo.save(region);
    		}
    		
        return new ResponseEntity<Region>(HttpStatus.CREATED);
    }
    
    @CrossOrigin
    @DeleteMapping(value = "/region")
    public ResponseEntity<Region> deleteregion(@RequestParam("deleteid") String deleteid) {
		logger.info("deleteregion requested for " +  deleteid);
    		Region region = regionrepo.findById(deleteid);
    		regionrepo.delete(region);
    		return new ResponseEntity<Region>(HttpStatus.ACCEPTED);
    }
	
}
