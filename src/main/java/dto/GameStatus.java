package dto;

import domain.ParticipantsRole;
import java.util.List;

public record GameStatus(ParticipantsRole role, String name, List<String> cards, int scoreSum) {
}
