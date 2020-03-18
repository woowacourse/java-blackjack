package blackjack.domain.rule;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.result.BlackJackResult;

public class ResultMatcher {

    public static BlackJackResult match(Player player, Dealer dealer) {
        if (!player.isBusted()) {
            return matchPlayerNotBusted(player, dealer);
        }
        return BlackJackResult.LOSE;
    }

    private static BlackJackResult matchPlayerNotBusted(Player player, Dealer dealer) {
        if (player.isBlackJack() && dealer.isBlackJack()) {
            return BlackJackResult.DRAW;
        }
        if (player.isBlackJack()) {
            return BlackJackResult.BLACKJACK;
        }
        return matchByScore(player, dealer);
    }

    private static BlackJackResult matchByScore(Player player, Dealer dealer) {
        if (player.calculateScore() > dealer.calculateScore()) {
            return BlackJackResult.WIN;
        }
        if (player.calculateScore() < dealer.calculateScore()) {
            return BlackJackResult.LOSE;
        }
        return BlackJackResult.DRAW;
    }

}
