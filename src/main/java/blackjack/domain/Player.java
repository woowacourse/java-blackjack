package blackjack.domain;

import java.util.List;

public class Player {
    private final Name name;
    private final Hand hand;

    private Player(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public static Player of(Name name) {
        return new Player(name, Hand.init());
    }

    public void receiveCards(List<TrumpCard> cards) {
        for (TrumpCard card : cards) {
            hand.receive(card);
        }
    }

    public void receiveCard(TrumpCard card) {
        hand.receive(card);
    }

    public int countCards() {
        return hand.countCards();
    }

    public int score() {
        return hand.calculateScore();
    }

    public String name() {
        return name.getName();
    }

    public boolean canHit() {
        return score() <= 21;
    }

    public List<TrumpCard> getCards() {
        return hand.getCards();
    }
}
