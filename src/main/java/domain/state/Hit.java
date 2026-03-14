package domain.state;

import domain.card.Card;
import domain.member.Hand;

public class Hit extends Running {

    public Hit(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(Card card) {
        Hand newHand = hand.appendCard(card);
        int score = newHand.calculateTotalValue();

        if (score >= BUST_CONDITION) {
            return new Bust(newHand);
        }
        if (score == BLACKJACK_CONDITION) {
            return judgeInitialState(newHand);
        }
        return new Hit(newHand);
    }

    private State judgeInitialState(Hand newHand) {
        if (newHand.size() == INITIAL_CARDS_COUNT) {
            return new Blackjack(newHand);
        }
        return new Stay(newHand);
    }

    @Override
    public State stay() {
        return new Stay(hand);
    }
}
