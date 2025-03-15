package game;

import card.Card;
import java.util.List;
import java.util.Objects;

public class Player {

    private final String name;
    private final Hand hand;
    private final Betting betting;

    public Player(String name) {
        this(name, 0);
    }

    public Player(String name, int bettingMoney) {
        this.name = name;
        this.hand = new Hand();
        this.betting = new Betting(bettingMoney);
    }

    public void draw(List<Card> cards) {
        hand.drawCard(cards);
    }

    public int getCardsCount() {
        return hand.getCardsCount();
    }

    public int calculateTotalPoints() {
        return hand.calculate();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public int evaluate(GameResult gameResult) {
        return betting.evaluate(gameResult);
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public int getPlayerBettingMoney() {
        return betting.getBetting();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Player player)) {
            return false;
        }
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
