package fr.mns.locmns.domain.repository.location;

import fr.mns.locmns.domain.model.location.Location;
import fr.mns.locmns.dto.location.LocationSearchRequest;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class LocationCustomRepositoryImpl implements LocationCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Location> search(LocationSearchRequest request) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Location> criteriaQuery = criteriaBuilder.createQuery(Location.class);
        Root<Location> root = criteriaQuery.from(Location.class);
        List<Predicate> predicates = new ArrayList<>();

        if (request.getUserId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("user").get("id"), request.getUserId()));
        }

        if (StringUtils.hasText(request.getSearch())) {
            predicates.add(criteriaBuilder.or(
                    criteriaBuilder.like(root.get("materials").get("material").get("name"), getLikeValue(request.getSearch())),
                    criteriaBuilder.like(root.get("user").get("lastname"), getLikeValue(request.getSearch())),
                    criteriaBuilder.like(root.get("user").get("firstname"), getLikeValue(request.getSearch()))
            ));
        }

        if (request.getStatus() != null) {
            predicates.add(criteriaBuilder.equal(root.get("status"), request.getStatus()));
        }

        if (request.getPrevisionnalStartingDate() != null) {
            predicates.add(criteriaBuilder.equal(root.get("previsionnalStartingDate"), request.getPrevisionnalStartingDate()));
        }

        if (request.getCreationDate() != null) {
            predicates.add(criteriaBuilder.equal(root.get("creationDate"), request.getCreationDate()));
        }

        Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        criteriaQuery.where(finalPredicate);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    private String getLikeValue(String value) {
        return "%" + value + "%";
    }
}
