package blackjack.domain;

import java.util.Objects;

// TODO 성격에 따라 패키지 나누어보기
public class Card {
    private static final int MAX_CARD_VALUE = 13;

    private final Kind kind;
    private Value value;

    private Card(Kind kind, Value value) {
        this.kind = kind;
        this.value = value;
    }

    public static Card create(int cardOrder) {
        Kind kind = Kind.findKind(cardOrder / MAX_CARD_VALUE);
        int cardNumber = cardOrder % MAX_CARD_VALUE + 1;
        Value value = Value.findValue(cardNumber);

        return new Card(kind, value);
    }

    // TODO setter공개된 것과 달라보이지 않음
    public void downgradeAce() {
        value = Value.ACELOW;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return kind == card.kind && value == card.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(kind, value);
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
