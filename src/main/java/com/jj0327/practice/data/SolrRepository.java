package com.jj0327.practice.data;

import com.jj0327.practice.entity.solr.House;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

public interface SolrRepository extends SolrCrudRepository<House, Integer> {

    List<House> findByCustId(int custId);
}
