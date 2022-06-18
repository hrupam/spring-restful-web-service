package com.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    //    Versioning using URI -- start
    @GetMapping("/v1/person")
    public PersonV1 getPersonV1() {
        return new PersonV1("Rupam Hari");
    }

    @GetMapping("/v2/person")
    public PersonV2 getPersonV2() {
        return new PersonV2(new Name("Rupam", "Hari"));
    }
    //    Versioning using URI -- end

    //    Versioning using Request Param -- start
    @GetMapping(value = "/person/param", params = "version=1")
    public PersonV1 paramV1() {
        return new PersonV1("Rupam Hari");
    }

    @GetMapping(value = "/person/param", params = "version=2")
    public PersonV2 paramV2() {
        return new PersonV2(new Name("Rupam", "Hari"));
    }
    //    Versioning using Request Param -- end

    //    Versioning using Request header -- start
    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 headerV1() {
        return new PersonV1("Rupam Hari");
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 headerV2() {
        return new PersonV2(new Name("Rupam", "Hari"));
    }
    //    Versioning using Request header -- end

    //    Versioning using Accept header (Media type versioning aka Content negotiation or accept header) -- start
    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
    public PersonV1 producesV1() {
        return new PersonV1("Rupam Hari");
    }

    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
    public PersonV2 producesV2() {
        return new PersonV2(new Name("Rupam", "Hari"));
    }
    //    Versioning using Accept header -- end
}
