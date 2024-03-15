package blackjack.domain.deck;

public class Card {

    private final Kind kind;
    private final Value value;

    public Card(Kind kind, Value value) {
        this.kind = kind;
        this.value = value;
    }

    public String getCardName() {
        return value.getValueName() + kind.getKindName();
    }

    public int getScore() {
        return value.getScore();
    }

    public Kind getKind() {
        return kind;
    }

    public Value getValue() {
        return value;
    }
}
