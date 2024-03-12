package blackjack.domain.participants;

import blackjack.domain.card.Card;
import java.util.ArrayList;

public class Dealer implements GameParticipant {

    private static final int MAX_RECEIVE_SCORE = 17;
    private static final Name name = new Name("딜러");

    private final Hands hands;

    public Dealer() {
        this.hands = new Hands(new ArrayList<>());
    }

    @Override
    public void receiveHands(Hands newHands) {
        this.hands.receiveHands(newHands);
    }

    @Override
    public void hit(Card card) {
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

    @Override
    public boolean isBurst() {
        return calculateScore() > Player.MAX_SCORE;
    }

    @Override
    public int getHandsSize() {
        return hands.size();
    }

    public Name getName() {
        return name;
    }

    public Hands getHands() {
        return hands;
    }

}
