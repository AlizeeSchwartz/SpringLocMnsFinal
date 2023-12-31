package fr.mns.locmns.domain.repository.common;

import fr.mns.locmns.domain.model.common.BaseEntity;
import fr.mns.locmns.tools.exception.BadRequestException;
import org.springframework.data.jpa.repository.JpaRepository;

// Ici on va créer une interface qui sera utilisée par tous nos repository
// On "type" notre interface avec 'T' en précisant que T est une classe qui étends BaseEntity (notre mappedsuperclass)
// On extends de JpaRepository en précisant T et Integer (le type de notre ID)
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Integer> {

    // On définit une méthode par défaut  qui sera accessible à tous les repos
    default T findByIdNullSafe(Integer id) {
        if (id == null) {
            return null;
        }
        return findById(id).orElse(null);
    }
    //méthode qui permet de ne pas avoir l'exception qui peut tomber avec findById si on passe un id null
    // permet de ne pas devoir faire des if(id == null) throw new ...
    // on optimise !!

    default T findByIdWithException (Integer id, String type){
        T entity = findByIdNullSafe(id);
        if(entity == null){
            throw new BadRequestException("Incorrect " + type);
        }
        return entity;
    }
}
