package blackjackgame.domain.game;

import blackjackgame.domain.user.Dealer;
import blackjackgame.domain.user.DealerStatus;
import blackjackgame.domain.user.Player;
import blackjackgame.domain.user.PlayerStatus;
import blackjackgame.domain.user.Players;
import blackjackgame.domain.user.User;

public class Referee {
    private static final double BLACKJACK_WIN_BONUS_PERCENTAGE = 1.5;

    private final Players players;
    private final Dealer dealer;

    public Referee(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void judgeWinner() {
        for (Player player : players.getPlayers()) {
            judgeWinnerBetweenDealer(player);
        }
    }

    private void judgeWinnerBetweenDealer(Player player) {
        if (isDealerWin(player)) {
            player.lose();
            return;
        }
        if (isPlayerWin(player)) {
            applyPlayerWinByHands(player);
            return;
        }
        player.draw();
    }

    private boolean isDealerWin(Player player) {
        boolean isPlayerBust = PlayerStatus.BUST == player.getStatus();
        boolean isDealerHasHigherScore = dealer.isLessThanBustScore() && player.getScore() < dealer.getScore();
        boolean isOnlyDealerBlackJack = isBlackJack(dealer) && !isBlackJack(player);
        return isPlayerBust || isDealerHasHigherScore || isOnlyDealerBlackJack;
    }

    private boolean isBlackJack(User user) {
        return user.cards().size() == 2 && user.getScore() == 21;
    }

    private boolean isPlayerWin(Player player) {
        boolean isDealerBust = DealerStatus.BUST == dealer.getStatus();
        boolean isPlayerHasHigherScore = player.isLessThanBustScore() && player.getScore() > dealer.getScore();
        boolean isOnlyPlayerBlackJack = isBlackJack(player) && !isBlackJack(dealer);
        return isDealerBust || isPlayerHasHigherScore || isOnlyPlayerBlackJack;
    }

    private void applyPlayerWinByHands(Player player) {
        if (isBlackJack(player)) {
            player.winWithBlackJack(BLACKJACK_WIN_BONUS_PERCENTAGE);
            return;
        }
        player.win();
    }
}
