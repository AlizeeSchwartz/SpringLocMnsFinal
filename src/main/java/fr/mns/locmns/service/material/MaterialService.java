package fr.mns.locmns.service.material;

import fr.mns.locmns.dto.material.material.MaterialCreateRequest;
import fr.mns.locmns.dto.material.material.MaterialDetails;
import fr.mns.locmns.dto.material.material.MaterialSearchRequest;
import fr.mns.locmns.dto.material.material.MaterialUpdateRequest;

import java.util.List;

public interface MaterialService {
    MaterialDetails getDetails(Integer id);

    List<MaterialDetails> getList();

    MaterialDetails create(MaterialCreateRequest request);

    MaterialDetails update(Integer id, MaterialUpdateRequest request);

    void delete(Integer id);

    List<MaterialDetails> search(MaterialSearchRequest request);
}
