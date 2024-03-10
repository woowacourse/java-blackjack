package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.common.Name;
import blackjack.domain.card.Cards;

import java.util.List;

public abstract class Player implements CardReceivable {
    private static final int BUST_SIZE = 21;
    private static final int CHANGE_A_VALUE = 10;

    protected final Name name;
    protected final Cards cards;

    public Player(final Name name, final Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public int calculateScore() {
        final int sum = cards.sum();
        if (cards.containAce() && sum + CHANGE_A_VALUE <= BUST_SIZE) {
            return sum + CHANGE_A_VALUE;
        }
        return sum;
    }

    public void drawCard(final Card card) {
        cards.add(card);
    }

    public String getNameAsString() {
        return name.asString();
    }

    public Name getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards.toList();
    }

    public boolean isBust() {
        return cards.sum() > BUST_SIZE;
    }
}
