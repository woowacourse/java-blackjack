package blackjack.domain.participants;


import blackjack.domain.card.Card;
import java.util.ArrayList;

public class Player implements GameParticipant {

    public static final int MAX_SCORE = 21;

    private final Name name;
    private final Hands hands;

    public Player(Name name) {
        this.name = name;
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
        return calculateScore() < MAX_SCORE;
    }

    @Override
    public boolean isBurst() {
        return calculateScore() > 21;
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
