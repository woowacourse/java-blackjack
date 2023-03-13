package domain.player;

import domain.card.Card;
import domain.card.Hand;

import java.util.List;
import java.util.Objects;

public abstract class Player {
    private final Hand hand;
    private final Name name;
    private final Bet bet;

    public Player(Hand hand, Name name, Bet bet) {
        this.hand = hand;
        this.name = name;
        this.bet = bet;
    }

    public void draw(Card card) {
        hand.addCard(card);
    }

    public int getScore() {
        return hand.getScore();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public Card getCardIndexOf(int index) {
        return hand.getCardIndexOf(index);
    }

    public abstract boolean isNameEqualTo(String playerName);

    public String getName() {
        return this.name.getName();
    }

    public Bet getBet() {
        return bet;
    }

    public Bet win() {
        return bet;
    }

    public Bet lose() {
        return bet.toNegative();
    }

    public Bet draw() {
        return Bet.empty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Bet blackJack() {
        return bet.multiply(1.5f);
    }

    @Override
    public String toString() {
        return "Player{" +
                "hand=" + hand +
                ", name=" + name +
                ", bet=" + bet +
                '}';
    }
}
