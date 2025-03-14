package domain;

import static domain.GameResultStatus.DRAW;
import static domain.GameResultStatus.LOSE;
import static domain.GameResultStatus.WIN;

import java.util.Objects;

public class Player extends Participant {

    private final String name;

    public Player(String name, Hand hand) {
        super(hand);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean hasBustCards() {
        return hand.isBust();
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
