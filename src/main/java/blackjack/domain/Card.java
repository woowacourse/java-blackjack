package blackjack.domain;

public class Card {
    private static final int MAX_CARD_VALUE = 13;

    private final Kind kind;
    private final Value value;

    private Card(Kind kind, Value value) {
        this.kind = kind;
        this.value = value;
    }

    public static Card create(int cardOrder) {
        Kind kind = Kind.findKind((cardOrder-1) / MAX_CARD_VALUE);
        Value value = Value.findValue(cardOrder % MAX_CARD_VALUE);

        return new Card(kind, value);
    }

    public int getScore(){
        return value.getScore();
    }

    public Kind getKind() {
        return kind;
    }

    public Value getValue() {
        return value;
    }
}
