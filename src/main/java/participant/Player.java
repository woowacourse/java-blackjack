package participant;

import card.Hand;
import result.GameResultStatus;
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

    public GameResultStatus calculateScore(Dealer dealer) {
        if (isBust()) {
            return GameResultStatus.LOSE;
        }
        if (dealer.isBust()) {
            return GameResultStatus.WIN;
        }
        if (isBlackJack() && dealer.isBlackJack()) {
            return GameResultStatus.DRAW;
        }
        if (isBlackJack()) {
            return GameResultStatus.WIN;
        }
        if (dealer.isBlackJack()) {
            return GameResultStatus.LOSE;
        }
        return compare(dealer);
    }

    public GameResultStatus compare(Dealer dealer) {
        if (getScore().isGreaterThen(dealer.getScore())) {
            return GameResultStatus.WIN;
        }
        if (getScore().isEqualTo(dealer.getScore())) {
            return GameResultStatus.DRAW;
        }
        return GameResultStatus.LOSE;
    }

    @Override
    public Hand openInitialHand() {
        return hand;
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
