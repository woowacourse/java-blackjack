package blackjack.domain.participant.human;

import blackjack.domain.cards.Cards;
import blackjack.domain.cards.card.Card;
import blackjack.domain.participant.human.name.Name;
import blackjack.domain.result.Point;
import java.util.List;

public abstract class Human {
    private static final Point BLACKJACK_POINT = Point.fromValue(21);
    protected final Name name;
    protected final Cards cards;

    protected Human(Cards cards, Name name) {
        this.name = name;
        this.cards = cards;
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public void addCards(final List<Card> addedCards) {
        addedCards.forEach(cards::add);
    }

    public Card getInitCard() {
        return cards.getFirstCard();
    }

    public boolean isBust() {
        return getPoint().compareTo(BLACKJACK_POINT) > 0;
    }

    public boolean isBlackjack() {
        return getPoint().equals(BLACKJACK_POINT);
    }

    public Point getPoint() {
        return Point.fromCards(cards);
    }

    public String getName() {
        return name.get();
    }

    public List<Card> getRawCards() {
        return cards.getCopy();
    }
}
