package blackjack.domain.participant;

import blackjack.domain.Score;
import blackjack.domain.card.Card;
import java.util.List;

public abstract class Participant {
    protected final Name name;
    protected Hand hand;

    public Participant(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public void hit(Card card) {
        hand = hand.add(card);
    }

    public void hit(List<Card> cards) {
        hand = hand.add(cards.toArray(Card[]::new));
    }

    public Score calculateScore() {
        return hand.calculateScore();
    }

    public List<Card> showCards() {
        return hand.getCards();
    }

    public boolean isBust() {
        return hand.calculateScore().isBust();
    }

    public Name getName() {
        return name;
    }

    public boolean isBlackjack() {
        return hand.getCards().size() == 2 && hand.calculateScore().isBlackjack();
    }
}
