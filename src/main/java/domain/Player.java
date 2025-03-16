package domain;

import static domain.GameResultStatus.DRAW;
import static domain.GameResultStatus.LOSE;
import static domain.GameResultStatus.WIN;

import java.util.List;
import java.util.Objects;

public class Player extends Participant {

    private final BettingHistory bettingHistory;

    public Player(String name, Hand hand, BettingHistory bettingHistory) {
        super(name, hand);
        this.bettingHistory = bettingHistory;
    }

    public Player(String name, BettingHistory bettingHistory) {
        super(name, Hand.createEmpty());
        this.bettingHistory = bettingHistory;
    }

    public Player(String name, BettingMoney bettingMoney) {
        this(name, new BettingHistory(bettingMoney));
    }

    public Player(String name, Hand hand) {
        this(name, Hand.createEmpty(), null);
    }

    public List<Card> getDealCards() {
        return hand.getCards();
    }

    public boolean hasBustCards() {
        return hand.isBust();
    }

    public boolean isPossibleHit() {
        return !hand.isBust();
    }

    @Override
    public boolean isPossibleDraw() {
        return !hand.isBust();
    }

    public GameResultStatus calculateResultStatus(Hand dealerHand) {
        if (hand.isBust()) {
            return LOSE;
        }
        if (dealerHand.isBust()) {
            return WIN;
        }
        return compareCardsSum(hand, dealerHand);
    }

    public Profit calculateProfit(Hand hand) {
        return null;
    }

    private GameResultStatus compareCardsSum(Hand playerHand, Hand dealerHand) {
        if (playerHand.isLargerThan(dealerHand)) {
            return WIN;
        }
        if (dealerHand.isLargerThan(playerHand)) {
            return LOSE;
        }
        return DRAW;
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
