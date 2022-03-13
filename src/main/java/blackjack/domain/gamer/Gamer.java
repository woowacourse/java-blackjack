package blackjack.domain.gamer;

import java.util.List;
import java.util.Objects;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Gamer {

    private static final String NAME_INPUT_ERROR_MESSAGE = "참가자의 이름으로 공백이나 빈 문자열은 입력할 수 없습니다.";

    protected final String name;
    protected final Cards cards;

    public Gamer(String name, Cards cards) {
        validateName(name);
        this.name = name;
        this.cards = cards;
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(NAME_INPUT_ERROR_MESSAGE);
        }
    }

    public abstract void drawCard(Card card);

    public abstract boolean canDraw();

    public int calculateScore() {
        return cards.calculateScore();
    }

    public boolean isBust() {
        return cards.calculateScore() > 21;
    }

    public boolean isBlackjack() {
        return cards.calculateScore() == 21;
    }

    public abstract List<Card> getViewCard();

    public abstract void win();

    public abstract void draw();

    public abstract void lose();

    public abstract String getWinDrawLoseString();

    public Cards getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Gamer gamer = (Gamer)o;
        return Objects.equals(name, gamer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
