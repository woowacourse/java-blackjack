package blackjack.domain;

import java.util.List;

public class Dealer extends Participant {
    public static final int DEALER_HIT = 16;

    public Dealer(List<Card> cards) {
        super("딜러", cards);
    }

    @Override
    public boolean isAvailableToTake() {
        return sumCards() <= DEALER_HIT;
    }

    public int calculateProfits(Players players) {
        int profits = 0;
        for (Player player : players.values()) {
            Outcome outcome = result(player.sumCardsForResult());
            profits += debtOrRevenueFromPlayer(outcome, player.getBetMoney());
        }
        return profits;
    }

    public int debtOrRevenueFromPlayer(Outcome outcome, int betMoney) {
        if (Outcome.WIN.equals(outcome)) {
            return betMoney;
        }
        if (Outcome.LOSE.equals(outcome)) {
            return -betMoney;
        }
        return betMoney - betMoney;
    }
}
