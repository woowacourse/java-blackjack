package blackjack.domain.result;

import static blackjack.domain.result.GameOutcome.BLACKJACK_WIN;
import static blackjack.domain.result.GameOutcome.DRAW;
import static blackjack.domain.result.GameOutcome.LOSE;
import static blackjack.domain.result.GameOutcome.WIN;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class GameJudge {
    public GameOutcome judge(Player player, Dealer dealer) {
        if (isBlackjackDraw(player, dealer)) {
            return DRAW;
        }
        if (player.isBlackjack()) {
            return BLACKJACK_WIN;
        }
        if (dealer.isBlackjack()) {
            return LOSE;
        }
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        return compareScore(player, dealer);
    }

    private boolean isBlackjackDraw(Player player, Dealer dealer) {
        return player.isBlackjack() && dealer.isBlackjack();
    }

    private GameOutcome compareScore(Player player, Dealer dealer) {
        if (player.getScore() > dealer.getScore()) {
            return WIN;
        }
        if (player.getScore() < dealer.getScore()) {
            return LOSE;
        }
        return DRAW;
    }
}
