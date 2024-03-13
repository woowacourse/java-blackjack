package blackjack.domain.game.result;

import blackjack.domain.betting.Money;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class PlayerWin implements GameResult {

    private static final PlayerWin instance = new PlayerWin();

    private PlayerWin() {
    }

    @Override
    public boolean isSatisfy(Dealer dealer, Player player) {
        if (player.isBlackjack()) {
            return false;
        }
        if (player.isBusted()) {
            return false;
        }
        if (dealer.isBusted()) {
            return true;
        }
        if (!player.isBlackjack() && !dealer.isBusted()) {
            return player.calculateScore() > dealer.calculateScore();
        }
        return false;
    }

    @Override
    public Money calculatePrize(Money bet) {
        return bet;
    }

    public static PlayerWin getInstance() {
        return instance;
    }
}
