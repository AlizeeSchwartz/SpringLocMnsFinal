package fr.mns.locmns.tools.exception;

import lombok.experimental.StandardException;

// Lombok : permet de générer les constructeurs pour une exception standard
@StandardException
public class BadRequestException extends RuntimeException {
}
