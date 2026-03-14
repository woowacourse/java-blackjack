package dto;

import domain.Hand;
import domain.ParticipantsRole;

public record GameStatus(ParticipantsRole role, String name, Hand hand) {
}
