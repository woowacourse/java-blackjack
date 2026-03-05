package domain.card;

public class Card {
    private final CardValue value;
    private final CardKind kind;

    public Card(String value, String kind) {
        this.value = CardValue.of(value);
        this.kind = CardKind.of(kind);
    }

    public int getValue() {
        return value.getNumber();
    }

    public String getCardInfo() {
        return value.getValue() + kind.getKind();
    }

    public boolean isAce() {
        return value == CardValue.ACE;
    }
}
