package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import java.util.List;

public abstract class Participant {
    private final Name name;
    private final Hand hand;

    public Participant(Name name, Deck deck) {
        this.name = name;
        this.hand = new Hand(deck.drawInitialCards());
    }

    public abstract List<Card> getInitialOpenedCards();

    public abstract boolean canHit();

    public boolean cannotHit() {
        return !canHit();
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public void addCard(Deck deck) {
        Card card = deck.drawCard();
        hand.addCard(card);
    }

    public int getScore() {
        return hand.getScore();
    }

    protected List<Card> getCardsByCount(int count) {
        List<Card> cards = getCards();
        return cards.subList(0, count);
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    protected boolean isTotalScoreGreaterThan(int score) {
        return hand.isTotalScoreGreaterThan(score);
    }
}
