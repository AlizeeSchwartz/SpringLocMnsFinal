package fr.mns.locmns.controller.material;

import fr.mns.locmns.dto.material.material.MaterialCreateRequest;
import fr.mns.locmns.dto.material.material.MaterialDetails;
import fr.mns.locmns.dto.material.material.MaterialSearchRequest;
import fr.mns.locmns.dto.material.material.MaterialUpdateRequest;
import fr.mns.locmns.service.material.MaterialService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "material")
@AllArgsConstructor
public class MaterialController {

    private final MaterialService materialService;

    @GetMapping("{id}")
    public MaterialDetails getDetails(@PathVariable Integer id) {
        return materialService.getDetails(id);
    }

    @GetMapping
    public List<MaterialDetails> getList() {
        return materialService.getList();
    }

    @PutMapping
    public MaterialDetails create(@RequestBody MaterialCreateRequest request) {
        return materialService.create(request);
    }

    @PostMapping("{id}")
    public MaterialDetails update(@PathVariable Integer id, @RequestBody MaterialUpdateRequest request) {
        return materialService.update(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        materialService.delete(id);
    }

    @PostMapping
    public List<MaterialDetails> search(@RequestBody MaterialSearchRequest request) {
        return materialService.search(request);
    }
}
