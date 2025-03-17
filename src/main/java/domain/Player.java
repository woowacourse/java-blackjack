package domain;

import java.util.List;
import java.util.Objects;

public class Player extends Participant {

    private final BettingMoney bettingMoney;

    public Player(String name, Hand hand, BettingMoney bettingMoney) {
        super(name, hand);
        this.bettingMoney = bettingMoney;
    }

    public Player(String name, BettingMoney bettingMoney) {
        this(name, Hand.createEmpty(), bettingMoney);
    }

    public Player(String name, Hand hand) {
        this(name, hand, new BettingMoney(0));
    }

    public Player(String name) {
        this(name, Hand.createEmpty(), new BettingMoney(0));
    }

    public List<Card> getDealCards() {
        return hand.getCards();
    }

    public boolean isImpossibleHit() {
        return hand.isBust();
    }

    public Profit calculateProfit(Hand dealerHand) {
        GameResultStatus gameResultStatus = hand.calculateGameResultStatus(dealerHand);
        return bettingMoney.calculateProfit(gameResultStatus);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Player player = (Player) object;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
