package domain.participant.player;

import domain.participant.Dealer;
import domain.participant.HandCards;
import domain.participant.Participant;
import domain.participant.WinStatus;
import domain.vo.Name;

public class Player extends Participant {
    private final Name name;
    private WinStatus winStatus;

    public Player(Name name, HandCards handCards) {
        super(handCards);
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    public WinStatus getWinStatus() {
        return winStatus;
    }

    public void calculateResult(Dealer dealer) {
        if (isBust()) {
            winStatus = WinStatus.LOSS;
            return;
        }

        if (this.isBlackjack() && !dealer.isBlackjack() || dealer.isBust() || getScore() > dealer.getScore()) {
            winStatus = WinStatus.WIN;
            return;
        }

        if (getScore() == dealer.getScore()) {
            winStatus = WinStatus.DRAW;
            return;
        }

        winStatus = WinStatus.LOSS;
    }
}
