package domain;

import java.util.List;

public record GameResult(ParticipantName name, List<TrumpCard> trumpCards, Score cardSum) {
}
