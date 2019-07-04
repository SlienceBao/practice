package com.jj0327.practice.controller;

import com.jj0327.practice.data.SolrRepository;
import com.jj0327.practice.entity.solr.House;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jinbao
 * @date 2019/7/4 17:13
 * @description: solr
 */
@RestController
@RequestMapping("/solr")
public class SolrController {

    @Resource
    private SolrRepository solrRepository;

    @GetMapping("/getByCustId")
    public List<House>  solr(int custId) {
        System.out.println(custId);
        List<House> byCustId = solrRepository.findByCustId(custId);
        return byCustId;
    }
}
