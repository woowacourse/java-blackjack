package blackjack.domain;

import java.util.Objects;

// TODO 성격에 따라 패키지 나누어보기
public class Card {
    private final Kind kind;
    private final Value value;

    public Card(Kind kind, Value value) {
        this.kind = kind;
        this.value = value;
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
