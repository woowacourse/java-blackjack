package dto;

import domain.Card;
import domain.ParticipantsRole;
import java.util.List;

public record GameStatus(ParticipantsRole role, String name, List<Card> cards, int scoreSum) {
}
