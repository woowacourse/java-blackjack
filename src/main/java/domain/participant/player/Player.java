package domain.player;

import domain.Participant;
import domain.vo.Name;

import java.util.List;

public class Player extends Participant {
    private WinStatus winStatus = WinStatus.DRAW;

    public Player(Name name) {
        this.name = name;
    }

    public void finalizeResult(int dealerScore) {
        if (isBust() || getMyScore() < dealerScore) {
            winStatus = WinStatus.LOSS;
        }

        if (getMyScore() > dealerScore) {
            winStatus = WinStatus.WIN;
        }
    }

    public String getName() {
        return name.getName();
    }

    public List<String> getCards() {
        return handCards.cardsToString();
    }
}
