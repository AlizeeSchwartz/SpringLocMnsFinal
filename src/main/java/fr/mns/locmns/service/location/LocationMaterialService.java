package fr.mns.locmns.service.location;

import fr.mns.locmns.dto.material.material.MaterialDetails;
import fr.mns.locmns.dto.material.material.MaterialSearchRequest;

import java.util.List;

public interface LocationMaterialService {
    List<MaterialDetails> getMaterials(Integer locationId);

    List<MaterialDetails> getAvailableMaterials(Integer locationId, MaterialSearchRequest request);

    void addMaterial(Integer locationId, Integer materialId);

    void removeMaterial(Integer locationId, Integer materialId);
}
