package domain;

import domain.card.TrumpCard;
import java.util.List;

public record ParticipantHandResult(String name, int score, List<TrumpCard> cards) {

}

