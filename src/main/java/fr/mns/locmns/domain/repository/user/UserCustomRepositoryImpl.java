package fr.mns.locmns.domain.repository.user;

import fr.mns.locmns.domain.model.user.User;
import fr.mns.locmns.dto.user.user.UserSearchRequest;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserCustomRepositoryImpl implements UserCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> search(UserSearchRequest request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(request.getSearch())) {
            predicates.add(criteriaBuilder.or(
                    criteriaBuilder.like(root.get("lastname"), getLikeValue(request.getSearch())),
                    criteriaBuilder.like(root.get("firstname"), getLikeValue(request.getSearch())),
                    criteriaBuilder.like(root.get("email"), getLikeValue(request.getSearch())),
                    criteriaBuilder.like(root.get("login"), getLikeValue(request.getSearch()))
            ));
        }

        if (request.getGender() != null) {
            predicates.add(criteriaBuilder.equal(root.get("gender"), request.getGender()));
        }

        if (request.getStatusId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("status").get("id"), request.getStatusId()));
        }

        if (request.getAffiliation() != null) {
            predicates.add(criteriaBuilder.equal(root.get("affiliation"), request.getAffiliation()));
        }

        Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        criteriaQuery.where(finalPredicate);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    private String getLikeValue(String value) {
        return "%" + value + "%";
    }
}
