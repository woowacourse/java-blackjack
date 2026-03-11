package domain.player;

import domain.card.Card;
import java.util.List;

public class Player {

    private final Name name;
    private final PlayerStatus playerStatus;

    private Player(String name, PlayerStatus playerStatus) {
        this.name = new Name(name);
        this.playerStatus = playerStatus;
    }

    public static Player create(String name, Hand hand, int amount) {
        Money money = new Money(amount);
        Bet bet = new Bet(money);
        PlayerStatus playerStatus = new PlayerStatus(hand, bet);
        return new Player(name, playerStatus);
    }

    public boolean isBust() {
        return playerStatus.isBust();
    }

    public List<Card> getCards() {
        return playerStatus.getCards();
    }

    public void addHand(Card card) {
        playerStatus.addHand(card);
    }

    public String getName() {
        return name.getName();
    }

    public int getTotalScore() {
        return playerStatus.getTotalScore();
    }

    public boolean isBlackjack() {
        return playerStatus.isBlackjack();
    }

    public int calculateProfit(Dealer dealer) {
        return playerStatus.calculateProfit(dealer);
    }
}
