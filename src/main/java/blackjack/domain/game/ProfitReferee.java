package blackjack.domain.game;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;

public class ProfitReferee {

    private static final double BLACKJACK = 1.5;
    private static final double WIN = 1;
    private static final double TIE = 0;
    private static final double LOSE = -1;

    private final Dealer dealer;

    public ProfitReferee(final Dealer dealer) {
        this.dealer = dealer;
    }

    public double profit(final Player player) {
        if (player.isBlackjack()) {
            return profitIfPlayerBlackjack();
        }

        if (player.isBusted()) {
            return profitIfPlayerBust();
        }

        return calculateIfPlayerStay(player);

    }

    private double profitIfPlayerBlackjack() {
        if (dealer.isBlackjack()) {
            return TIE;
        }
        return BLACKJACK;
    }

    private double profitIfPlayerBust() {
        if (dealer.isBusted()) {
            return TIE;
        }
        return LOSE;
    }

    private double calculateIfPlayerStay(final Player player) {
        if (player.currentScore()
                .isBiggerThan(dealer.currentScore()) || dealer.isBusted()) {
            return WIN;
        }
        if (dealer.currentScore()
                .isBiggerThan(player.currentScore())) {
            return LOSE;
        }
        return TIE;
    }
}
