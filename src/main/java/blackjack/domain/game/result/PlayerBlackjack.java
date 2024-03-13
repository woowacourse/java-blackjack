package blackjack.domain.game.result;

import blackjack.domain.betting.Money;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class PlayerBlackjack implements GameResult {

    private static final PlayerBlackjack instance = new PlayerBlackjack();

    private PlayerBlackjack() {
    }

    @Override
    public boolean isSatisfy(Dealer dealer, Player player) {
        return player.isBlackjack() && !dealer.isBlackjack();
    }

    @Override
    public Money calculatePrize(Money bet) {
        return bet.multiply(1.5);
    }

    public static PlayerBlackjack getInstance() {
        return instance;
    }
}
