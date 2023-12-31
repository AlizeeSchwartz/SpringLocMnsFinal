package fr.mns.locmns.domain.model.user;

import fr.mns.locmns.domain.model.common.NamedEntity;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

// L'annotation sert à indiquer que c'est une entity, c'est à dire une classe Java qui représente une table de ta DB, ici la table status
@Entity(name = "status")
// L'annotation Lombok Getter sert à générer les getters lors de la compilation
@Getter
// L'annotation Lombok Setter sert à générer les setters lors de la compilation
@Setter
public class Status extends NamedEntity {

}
