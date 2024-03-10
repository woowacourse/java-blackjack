package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.card.HandGenerator;

import java.util.List;

public abstract class Participant {
    private final Name name;
    private final Hand hand;

    public Participant(Name name, HandGenerator handGenerator) {
        this.name = name;
        this.hand = handGenerator.generate();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public void addCard(Deck deck) {
        Card card = deck.drawCard();
        hand.addCard(card);
    }

    public int getScore() {
        return hand.getOptimizedScore();
    }

    public abstract List<Card> getInitialOpenedCards();

    public abstract boolean canHit();

    protected List<Card> getCardsByCount(int count) {
        List<Card> cards = getCards();
        return cards.subList(0, count);
    }

    protected boolean isBlackjack() {
        return hand.isBlackjack();
    }

    protected boolean isTotalScoreGreaterThan(int score) {
        return hand.isTotalScoreGreaterThan(score);
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }
}
