package domain.game;

import domain.card.TrumpCard;
import domain.participant.ParticipantName;
import domain.participant.Score;
import java.util.List;

public record GameResult(ParticipantName name, List<TrumpCard> trumpCards, Score cardSum) {
}
