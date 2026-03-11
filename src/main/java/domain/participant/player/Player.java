package domain.participant.player;

import domain.participant.HandCards;
import domain.participant.Participant;
import domain.participant.WinStatus;
import domain.vo.Name;

public class Player extends Participant {
    private WinStatus winStatus = WinStatus.DRAW;

    public Player(Name name, HandCards handCards) {
        super(handCards);
        this.name = name;
    }

    public WinStatus getWinStatus() {
        return winStatus;
    }

    @Override
    public void finalizeResult(int dealerScore) {
        if (isBust() || getScore() < dealerScore) {
            winStatus = WinStatus.LOSS;
        }

        if (getScore() > dealerScore) {
            winStatus = WinStatus.WIN;
        }
    }
}
