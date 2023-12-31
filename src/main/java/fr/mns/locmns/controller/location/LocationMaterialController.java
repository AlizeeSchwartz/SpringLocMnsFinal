package fr.mns.locmns.controller.location;

import fr.mns.locmns.dto.material.material.MaterialDetails;
import fr.mns.locmns.dto.material.material.MaterialSearchRequest;
import fr.mns.locmns.service.location.LocationMaterialService;
import fr.mns.locmns.service.material.MaterialService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "materialRental")
@AllArgsConstructor
public class LocationMaterialController {

    private final LocationMaterialService locationMaterialService;
    private final MaterialService materialService;

    @GetMapping({"locationId"})
    public List<MaterialDetails> getMaterials(@PathVariable Integer locationId) {
        return locationMaterialService.getMaterials(locationId);
    }

    @GetMapping
    public List<MaterialDetails> getAvailableMaterials(@PathVariable Integer locationId, @RequestBody MaterialSearchRequest request) {
        return materialService.search(request);
    }

    @PostMapping("{id}")
    public void addMaterial(@PathVariable Integer locationId, @PathVariable Integer materialId) {
        locationMaterialService.addMaterial(locationId, materialId);
    }

    @DeleteMapping("{id}")
    public void removeMaterial(@PathVariable Integer locationId, @PathVariable Integer materialId) {
        locationMaterialService.removeMaterial(locationId, materialId);
    }
}
