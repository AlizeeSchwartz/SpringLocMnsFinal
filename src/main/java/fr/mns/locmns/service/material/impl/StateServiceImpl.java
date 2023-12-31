package fr.mns.locmns.service.material.impl;

import fr.mns.locmns.domain.model.material.State;
import fr.mns.locmns.domain.repository.material.StateRepository;
import fr.mns.locmns.dto.material.state.StateCreateRequest;
import fr.mns.locmns.dto.material.state.StateDetails;
import fr.mns.locmns.dto.material.state.StateUpdateRequest;
import fr.mns.locmns.service.material.StateService;
import fr.mns.locmns.tools.exception.BadRequestException;
import fr.mns.locmns.tools.exception.NotFoundException;
import fr.mns.locmns.tools.utils.MappingUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StateServiceImpl implements StateService {
    private final StateRepository stateRepository;

    @Override
    public StateDetails getDetails(Integer id) {
        State state = stateRepository.findByIdNullSafe(id);
        if (state == null) {
            throw new NotFoundException();
        }
        return MappingUtils.getStateDetails(state);
    }

    @Override
    public List<StateDetails> getList() {
        List<State> stateList = stateRepository.findAll();
        List<StateDetails> stateDetailsList = new ArrayList<>();
        for (State state : stateList) {
            StateDetails details = MappingUtils.getStateDetails(state);
            stateDetailsList.add(details);
        }
        return stateDetailsList;
    }

    @Override
    public StateDetails create(StateCreateRequest request) {
        if (!StringUtils.hasText(request.getName())) {
            throw new BadRequestException("Name should not be empty");
        }
        State state = new State();
        state.setName(request.getName());
        state = stateRepository.save(state);
        return MappingUtils.getStateDetails(state);
    }

    @Override
    public StateDetails update(Integer id, StateUpdateRequest request) {
        State state = stateRepository.findByIdNullSafe(id);
        if (state == null) {
            throw new NotFoundException();
        }
        if (!StringUtils.hasText(request.getName())) {
            throw new BadRequestException("Name should not be empty");
        }
        state.setName(request.getName());
        state = stateRepository.save(state);
        return MappingUtils.getStateDetails(state);
    }

    @Override
    public void delete(Integer id) {
        State state = stateRepository.findByIdNullSafe(id);
        if (state == null) {
            throw new NotFoundException();
        }
        stateRepository.delete(state);
    }
}
