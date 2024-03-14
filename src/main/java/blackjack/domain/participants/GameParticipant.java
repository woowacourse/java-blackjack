package blackjack.domain.participants;

import blackjack.domain.card.Card;

public abstract class GameParticipant {

    public static final int MAX_SCORE = 21;
    private static final int BLACKJACK_SIZE = 2;

    protected final Name name;
    protected final Hands hands;

    public GameParticipant(Name name, Hands hands) {
        this.name = name;
        this.hands = hands;
    }

    public abstract boolean canHit();

    public void receiveHands(Hands newHands) {
        this.hands.receiveHands(newHands);
    }

    public void hit(Card card) {
        hands.addCard(card);
    }

    public int calculateScore() {
        return hands.calculateScore();
    }

    public boolean isBurst() {
        return calculateScore() > MAX_SCORE;
    }

    public boolean isBlackjack() {
        return hands.size() == BLACKJACK_SIZE && hands.calculateScore() == MAX_SCORE;
    }

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
