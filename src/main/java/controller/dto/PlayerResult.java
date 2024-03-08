package controller.dto;

import domain.constants.Outcome;

public record PlayerResult(String name, Outcome outcome) {
}
