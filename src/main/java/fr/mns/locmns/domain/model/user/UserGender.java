package fr.mns.locmns.domain.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserGender {

    MALE("Male"),
    FEMALE("Female");

    private final String name;
}
