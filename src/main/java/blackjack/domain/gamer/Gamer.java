package blackjack.domain.gamer;

import java.util.Collections;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Gamer {

    private static final String NAME_INPUT_ERROR_MESSAGE = "참가자의 이름으로 공백이나 빈 문자열은 입력할 수 없습니다.";
    private static final int DRAWABLE_NUMBER = 21;

    protected final String name;
    protected final Cards cards;

    public Gamer(final String name, final Cards cards) {
        validateName(name);
        this.name = name;
        this.cards = cards;
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(NAME_INPUT_ERROR_MESSAGE);
        }
    }

    public abstract boolean canDraw();

    public void drawCard(final Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        return cards.calculateScore();
    }

    public boolean isBust() {
        return cards.calculateScore() > DRAWABLE_NUMBER;
    }

    public boolean isDealer() {
        return this instanceof Dealer;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }

    public String getName() {
        return name;
    }
}
