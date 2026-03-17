package domain.participant.player;

import domain.participant.Dealer;
import domain.participant.HandCards;
import domain.participant.Participant;
import domain.participant.WinStatus;
import domain.vo.Name;

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
}
