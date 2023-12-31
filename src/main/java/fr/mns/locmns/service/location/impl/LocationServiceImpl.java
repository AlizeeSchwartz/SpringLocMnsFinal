package fr.mns.locmns.service.location.impl;

import fr.mns.locmns.domain.model.location.Location;
import fr.mns.locmns.domain.model.location.LocationStatus;
import fr.mns.locmns.domain.model.user.User;
import fr.mns.locmns.domain.repository.location.LocationRepository;
import fr.mns.locmns.domain.repository.user.UserRepository;
import fr.mns.locmns.dto.location.LocationCreateRequest;
import fr.mns.locmns.dto.location.LocationDetails;
import fr.mns.locmns.dto.location.LocationSearchRequest;
import fr.mns.locmns.dto.location.LocationUpdateRequest;
import fr.mns.locmns.service.location.LocationService;
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
public class LocationServiceImpl implements LocationService {
    private LocationRepository locationRepository;
    private UserRepository userRepository;

    @Override
    public LocationDetails getDetails(Integer id) {
        Location location = locationRepository.findByIdNullSafe(id);
        if (location == null) {
            throw new NotFoundException();
        }
        return MappingUtils.getLocationDetails(location);
    }

    @Override
    public List<LocationDetails> getList() {
        List<Location> locationList = locationRepository.findAll();
        List<LocationDetails> locationDetailsList = new ArrayList<>();
        for (Location location : locationList) {
            LocationDetails details = MappingUtils.getLocationDetails(location);
            locationDetailsList.add(details);
        }
        return locationDetailsList;
    }

    @Override
    public LocationDetails create(LocationCreateRequest request) {
        if (!StringUtils.hasText(request.getReason())) {
            throw new BadRequestException("Reason should not be empty");
        }
        if (request.getPrevisionnalStartingDate() == null) {
            throw new BadRequestException("Previsionnal starting date should not be empty");
        }
        if (request.getPrevisionnalEndDate() == null) {
            throw new BadRequestException("Previsionnal ending date should not be empty");
        }

        User user = getUser(request.getUserId());

        Location location = new Location();
        location.setReason(request.getReason());
        location.setPrevisionnalStartingDate(request.getPrevisionnalStartingDate());
        location.setPrevisionnalEndDate(request.getPrevisionnalEndDate());
        location.setUser(user);
        location.setStatus(LocationStatus.DRAFT);
        location = locationRepository.save(location);
        return MappingUtils.getLocationDetails(location);
    }

    @Override
    public LocationDetails update(Integer id, LocationUpdateRequest request) {
        Location location = locationRepository.findByIdNullSafe(id);
        if (location == null) {
            throw new NotFoundException();
        }

        if (!StringUtils.hasText(request.getReason())) {
            throw new BadRequestException("Reason should not be empty");
        }
        if (request.getPrevisionnalStartingDate() == null) {
            throw new BadRequestException("Previsionnal starting date should not be empty");
        }
        if (request.getPrevisionnalEndDate() == null) {
            throw new BadRequestException("Previsionnal ending date should not be empty");
        }

        location.setReason(request.getReason());
        location.setPrevisionnalStartingDate(request.getPrevisionnalStartingDate());
        location.setPrevisionnalEndDate(request.getPrevisionnalEndDate());
        location.setExtensionDate(request.getExtensionDate());

        location = locationRepository.save(location);
        return MappingUtils.getLocationDetails(location);
    }

    @Override
    public void delete(Integer id) {
        Location location = locationRepository.findByIdNullSafe(id);
        if (location == null) {
            throw new NotFoundException();
        }
        locationRepository.delete(location);
    }

    @Override
    public LocationDetails userValidation(Integer id) {
        Location location = locationRepository.findByIdNullSafe(id);
        if (location == null) {
            throw new NotFoundException();
        }
        location.setStatus(LocationStatus.PENDING);
        location = locationRepository.save(location);

        return MappingUtils.getLocationDetails(location);
    }

    @Override
    public LocationDetails adminValidation(Integer id) {
        Location location = locationRepository.findByIdNullSafe(id);
        if (location == null) {
            throw new NotFoundException();
        }
        location.setStatus(LocationStatus.VALIDATED);
        location = locationRepository.save(location);

        return MappingUtils.getLocationDetails(location);
    }

    @Override
    public LocationDetails adminRejection(Integer id) {
        Location location = locationRepository.findByIdNullSafe(id);
        if (location == null) {
            throw new NotFoundException();
        }
        location.setStatus(LocationStatus.REJECTED);
        location = locationRepository.save(location);

        return MappingUtils.getLocationDetails(location);
    }

    private User getUser(Integer userId) {
        return userRepository.findByIdWithException(userId, "user");
    }

    @Override
    public List<LocationDetails> search(LocationSearchRequest request) {
        List<Location> locationList = locationRepository.search(request);
        List<LocationDetails> locationDetailsList = new ArrayList<>();
        for (Location location : locationList) {
            LocationDetails details = MappingUtils.getLocationDetails(location);
            locationDetailsList.add(details);
        }
        return locationDetailsList;
    }

    @Override
    public List<LocationDetails> searchToBeValidated(LocationSearchRequest request) {
        request.setStatus(LocationStatus.PENDING);
        return search(request);
    }

    @Override
    public List<LocationDetails> searchUserRental(Integer userId, LocationSearchRequest request) {
        request.setUserId(userId);
        return search(request);
    }
}
