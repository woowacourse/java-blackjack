package view;

import domain.card.Rank;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum RankMessage {
    TWO(Rank.TWO, "2"),
    THREE(Rank.THREE, "3"),
    FOUR(Rank.FOUR, "4"),
    FIVE(Rank.FIVE, "5"),
    SIX(Rank.SIX, "6"),
    SEVEN(Rank.SEVEN, "7"),
    EIGHT(Rank.EIGHT, "8"),
    NINE(Rank.NINE, "9"),
    TEN(Rank.TEN, "10"),
    JACK(Rank.JACK, "10"),
    QUEEN(Rank.QUEEN, "10"),
    KING(Rank.KING, "10"),
    ACE(Rank.ACE, "A");

    private final Rank rank;
    private final String message;

    private static final Map<Rank, String> rankMessage =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(view.RankMessage::getRank, view.RankMessage::getMessage)));


    RankMessage(final Rank rank, final String message) {
        this.rank = rank;
        this.message = message;
    }

    public static String getRankMessage(Rank rank) {
        return rankMessage.get(rank);
    }
    Rank getRank() {
        return rank;
    }

    String getMessage() {
        return message;
    }
}
