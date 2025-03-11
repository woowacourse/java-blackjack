package domain;

import java.util.List;

public record BlackjackResult(ParticipantName name, List<TrumpCard> trumpCards, Score cardSum) {
}
