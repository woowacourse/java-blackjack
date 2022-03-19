package blackjack.domain.participant.human;

import blackjack.domain.cards.Cards;
import blackjack.domain.cards.card.Card;
import blackjack.domain.participant.human.name.Name;
import blackjack.domain.result.Point;
import java.util.List;

public abstract class Human {
    private static final int BLACKJACK_NUMBER = 21;
    protected final Name name;
    protected final Cards cards;

    protected Human(Cards cards, Name name) {
        this.name = name;
        this.cards = cards;
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public Card getInitCard() {
        return cards.getFirstCard();
    }

    public boolean isBust() {
        return getPoint() > BLACKJACK_NUMBER;
    }

    public boolean isMaxPoint() {
        return getPoint() == BLACKJACK_NUMBER;
    }

    public int getPoint() {
        return new Point(cards).get();
    }

    public String getName() {
        return name.get();
    }

    public List<Card> getRawCards() {
        return cards.getCopy();
    }
}
