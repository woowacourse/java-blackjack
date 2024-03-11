package blackjack.domain;

import java.util.List;

public abstract class Participant {

    protected final Name name;
    protected final Hands hands;

    protected Participant(Name name) {
        this.name = name;
        this.hands = new Hands();
    }

    protected void addCard(Card card) {
        hands.addCard(card);

        if (hands.isBurst() && hands.hasHighAce()) {
            hands.downgradeAce();
        }
    }

    public List<Card> getHandsCards() {
        return hands.getHands();
    }

    public String getName() {
        return name.getName();
    }

    public int getHandsScore() {
        return hands.getHandsScore();
    }

    public boolean isBurst() {
        return hands.isBurst();
    }

    public boolean isNotBurst() {
        return !isBurst();
    }

    public boolean isBlackJack() {
        return hands.isBlackJack();
    }

    public boolean isNotBlackJack() {
        return !isBlackJack();
    }
}
