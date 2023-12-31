package fr.mns.locmns.controller.user;

import fr.mns.locmns.dto.user.status.StatusCreateRequest;
import fr.mns.locmns.dto.user.status.StatusDetails;
import fr.mns.locmns.dto.user.status.StatusUpdateRequest;
import fr.mns.locmns.service.user.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Permet de définir un Controller Rest
@RestController
// Permet de définir le path du controller
@RequestMapping(value = "status")
// Lombok (librairie) : génère un constructeur avec tous les arguments en paramètre ici StatusController(StatusService statusService)
@AllArgsConstructor
public class StatusController {

    // Injection de dépendance Spring via le constructeur (via le AllArgsConstructor de lombok)
    // Toujours mettre final car on ne doit pas pouvoir le modifier
    // Toujours pointer sur l'interface et pas l'implémentation
    private final StatusService statusService;

    // Permet de définir un mapping Get avec le path
    // Mettre les variables entre accolades, ici {id}
    @GetMapping("{id}")
    public StatusDetails getDetails(//Permet de récupérer la variable depuis le path
                                    @PathVariable Integer id) {
        return statusService.getDetails(id);
    }

    @GetMapping
    public List<StatusDetails> getList() {
        return statusService.getList();
    }

    @PutMapping
    public StatusDetails create(@RequestBody StatusCreateRequest request) {
        return statusService.create(request);
    }

    @PostMapping("{id}")
    public StatusDetails update(@PathVariable Integer id, @RequestBody StatusUpdateRequest request) {
        return statusService.update(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        statusService.delete(id);
    }
}
