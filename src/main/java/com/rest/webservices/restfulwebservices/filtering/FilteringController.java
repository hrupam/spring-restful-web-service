package com.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class FilteringController {

    //filter out - v3
    @GetMapping("/filtering")
    public MappingJacksonValue getSomeBean() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        return getSomeBeanMappingJacksonValueWithFilters(someBean, new HashSet<>(Arrays.asList("v1", "v2")));
    }

    //filter out - v1,v2
    @GetMapping("/filtering-list")
    public MappingJacksonValue getListOfSomeBeans() {
        List<SomeBean> list = Arrays.asList(new SomeBean("value11", "value21", "value31"), new SomeBean("value12", "value22", "value32"));

        return getSomeBeanMappingJacksonValueWithFilters(list, new HashSet<>(List.of("v3")));

    }

    private MappingJacksonValue getSomeBeanMappingJacksonValueWithFilters(Object object, Set<String> fieldsToShow) {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(object);
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fieldsToShow));
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
}
