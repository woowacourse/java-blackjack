package blackjack.domain;

import blackjack.domain.participants.GameParticipant;
import blackjack.domain.participants.Name;
import java.util.ArrayList;

public class Dealer implements GameParticipant {

    private static final int MAX_RECEIVE_SCORE = 17;

    private final Name name;
    private Hands hands;

    public Dealer(Name name) {
        this.name = name;
    }

    @Override
    public void receiveHands(Hands hands) {
        this.hands = hands;
    }

    @Override
    public void receiveCard(Card card) {
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
    public boolean canReceiveCard() {
        return calculateScore() < MAX_RECEIVE_SCORE;
    }

    public Name getName() {
        return name;
    }

    public Hands getHands() {
        return hands;
    }
}
