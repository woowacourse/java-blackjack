package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public abstract class Gamer {
    private static final String NAME_INPUT_ERROR_MESSAGE = "참가자의 이름으로 공백이나 빈 문자열은 입력할 수 없습니다.";

    private final String name;
    private final Cards cards;

    public Gamer(String name, Cards cards) {
        validate(name);
        this.name = name;
        this.cards = cards;
        hit();
        hit();
    }

    private void validate(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(NAME_INPUT_ERROR_MESSAGE);

        }
    }

    public boolean isBust() {
        return cards.calculateScore() > 21;
    }

    public boolean isBlackjack() {
        return cards.calculateScore() == 21;
    }

    public void hit() {
        cards.add(Card.draw());
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
}
