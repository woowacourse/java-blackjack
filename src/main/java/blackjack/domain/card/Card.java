package blackjack.domain.card;

public class Card {

    private final Number number;
    private final Kind kind;

    private Card(Number number, Kind kind) {
        this.number = number;
        this.kind = kind;
    }

    public static Card from(Number number, Kind kind) {
        return new Card(number, kind);
    }
}
