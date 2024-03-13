package blackjack.domain.game.result;

import blackjack.domain.betting.Money;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class Push implements GameResult {

    private static final Push instance = new Push();

    private Push() {

    }

    @Override
    public boolean isSatisfy(Dealer dealer, Player player) {
        if (player.isBusted() || dealer.isBusted()) {
            return false;
        }
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return true;
        }
        return player.calculateScore() == dealer.calculateScore();
    }

    @Override
    public Money calculatePrize(Money bet) {
        return Money.ZERO;
    }

    public static Push getInstance() {
        return instance;
    }
}
