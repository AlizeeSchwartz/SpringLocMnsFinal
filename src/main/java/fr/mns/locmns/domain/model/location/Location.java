package fr.mns.locmns.domain.model.location;

import fr.mns.locmns.domain.model.common.BaseEntity;
import fr.mns.locmns.domain.model.user.User;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

@Entity(name = "location")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Location extends BaseEntity {

    private String reason;
    private Date previsionnalStartingDate;
    private Date previsionnalEndDate;
    private Date extensionDate;
    private Date extensionValidationDate;
    @Enumerated(EnumType.STRING)
    private LocationStatus status;
    private Date decisionDate;

    @CreatedDate
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "location")
    private List<LocationMaterial> materials;
}
