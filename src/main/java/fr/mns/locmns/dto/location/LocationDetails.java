package fr.mns.locmns.dto.location;

import fr.mns.locmns.domain.model.location.LocationStatus;
import fr.mns.locmns.dto.user.user.UserDetails;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LocationDetails {
    private int id;
    private Date creationDate;
    private String reason;
    private Date previsionnalStartingDate;
    private Date previsionnalEndDate;
    private Date extensionDate;
    private Date extensionValidationDate;
    private Integer extensionDuration;
    private LocationStatus status;
    private Date decisionDate;

    private UserDetails user;
}
