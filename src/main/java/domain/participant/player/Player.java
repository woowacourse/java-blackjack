package domain.participant.player;

import domain.participant.Dealer;
import domain.participant.HandCards;
import domain.participant.Participant;
import domain.participant.WinStatus;
import domain.vo.Name;

import java.util.Objects;

public class Player extends Participant {
    private final Name name;

    public Player(Name name, HandCards handCards) {
        super(handCards);
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    public WinStatus decideWinStatus(Dealer dealer) {
        if (isBust()) {
            return WinStatus.LOSS;
        }

        if (isWinningCondition(dealer)) {
            return WinStatus.WIN;
        }

        if (isDrawCondition(dealer)) {
            return WinStatus.DRAW;
        }

        return WinStatus.LOSS;
    }

    private boolean isWinningCondition(Dealer dealer) {
        return (this.isBlackjack() && !dealer.isBlackjack())
                || dealer.isBust()
                || getScore() > dealer.getScore();
    }

    private boolean isDrawCondition(Dealer dealer) {
        return getScore() == dealer.getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player player)) return false;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
