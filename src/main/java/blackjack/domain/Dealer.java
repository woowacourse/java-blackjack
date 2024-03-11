package blackjack.domain;

import blackjack.domain.participants.GameParticipant;
import blackjack.domain.participants.Name;
import java.util.ArrayList;

public class Dealer implements GameParticipant {

    private static final int MAX_RECEIVE_SCORE = 17;
    private static final String DEALER_NAME = "딜러";

    private final Name name;
    private Hands hands;

    public Dealer() {
        this.name = new Name(DEALER_NAME);
    }

    @Override
    public void receiveHands(Hands hands) {
        hands.receiveHands(hands);
    }

    @Override
    public void hit(Card card) {
        if (hands == null) {
            hands = new Hands(new ArrayList<>());
        }
        hands.addCard(card);
    }

    @Override
    public int calculateScore() {
        return hands.calculateScore();
    }

    @Override
    public boolean canHit() {
        return calculateScore() < MAX_RECEIVE_SCORE;
    }

    public Name getName() {
        return name;
    }

    public Hands getHands() {
        return hands;
    }
}
