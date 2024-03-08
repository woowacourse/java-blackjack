package model.dto;

import model.participant.Name;

public record PlayerScoreResult(Name name, Victory victory) {
}
