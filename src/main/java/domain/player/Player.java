package domain.player;

import domain.card.Card;
import java.util.List;

public class Player {
    private final Name name;
    private final BettingHand bettingHand;

    private Player(Name name, BettingHand bettingHand) {
        this.name = name;
        this.bettingHand = bettingHand;
    }

    public static Player of(Name name, BettingHand bettingHand) {
        return new Player(name, bettingHand);
    }

    public boolean hasSameName(String otherName) {
        return name.getName().equals(otherName);
    }

    public boolean isBust() {
        return bettingHand.isBust();
    }

    public boolean isBlackjack() {
        return bettingHand.isBlackjack();
    }

    public boolean canHit() {
        return !bettingHand.isBust();
    }

    public void hit(Card card) {
        bettingHand.addCard(card);
    }

    public List<Card> cards() {
        return bettingHand.cards();
    }

    public String name() {
        return name.getName();
    }

    public int totalScore() {
        return bettingHand.totalScore();
    }

    public int calculateProfit(Dealer dealer) {
        return bettingHand.calculateProfit(dealer);
    }
}
