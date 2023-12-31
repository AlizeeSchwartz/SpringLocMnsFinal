package fr.mns.locmns.controller.utils;

import fr.mns.locmns.dto.utils.EnumerationDetails;
import fr.mns.locmns.service.utils.EnumerationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "enumeration")
@AllArgsConstructor
public class EnumerationController {

    private final EnumerationService service;


    @GetMapping("gender")
    public List<EnumerationDetails> getGenders() {
        return service.getGenders();
    }

    @GetMapping("affiliation")
    public List<EnumerationDetails> getAffiliations() {
        return service.getAffiliations();
    }

    @GetMapping("rentalStatus")
    public List<EnumerationDetails> getRentalStatus() {
        return service.getRentalStatus();
    }
}
