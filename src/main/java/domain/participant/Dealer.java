package domain.participant;

import domain.card.Card;
import java.util.List;

public class Dealer extends Participant {
    public Dealer(List<Card> hand) {
        super(hand);
    }

    public boolean canReceiveCard() {
        return calculateScore() <= 16;
    }

    public int finalProfit(Players players) {
        int result = 0;
        for (Player player : players.getPlayers()) {
            result += player.finalProfit(this);
        }
        return -result;
    }
}
