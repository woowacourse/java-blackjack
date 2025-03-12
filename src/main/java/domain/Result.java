package domain;

import java.util.List;

public record Result(ParticipantName name, List<TrumpCard> trumpCards, Score cardSum) {
}
