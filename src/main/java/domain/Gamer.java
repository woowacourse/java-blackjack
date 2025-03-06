package domain;

import java.util.List;

public abstract class Gamer {

    private final Nickname nickname;
    protected final Hand hand;

    public Gamer(final Nickname nickname) {
        this.nickname = nickname;
        this.hand = new Hand();
    }

    public int calculateSumOfRank() {
        return hand.getSumOfRank();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public void receiveInitialCards(List<Card> cards) {
        cards.forEach(hand::add);
    }

    public void hit(Card card) {
        hand.add(card);
    }

    public String getDisplayName() {
        return nickname.getDisplayName();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }
}

