package blackjack;

import blackjack.domain.HitOrStand;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackGame {

    public void run() {
        BlackjackRound blackjackRound = new BlackjackRound(Players.fromNames(InputView.inputPlayerName()));
        OutputView.printInitCard(blackjackRound.getCardStatus());
        Players players = blackjackRound.getPlayers();
        while (players.hasNext()) {
            playerHit(blackjackRound, players);
        }
        blackjackRound.dealerHit();
        OutputView.printHitResult(blackjackRound.getHitResult());
        OutputView.printResult(blackjackRound.judgeWinDrawLose());
    }

    private static void playerHit(BlackjackRound blackjackRound, Players players) {
        Player nowTurnPlayer = players.getNowPlayer();
        HitOrStand flag = HitOrStand.valueOf(InputView.inputHitOrStand(players.getNowPlayer().getName()));
        if (flag.isStand()) {
            players.next();
            return;
        }
        blackjackRound.playerHit();
        if (nowTurnPlayer.isBust() || nowTurnPlayer.isBlackjack()) {
            players.next();
        }
        OutputView.printPresentStatus(nowTurnPlayer.getName(), blackjackRound.toListCardDto(nowTurnPlayer),
                nowTurnPlayer.getCards().calculateScore(),
                nowTurnPlayer.isBust(), nowTurnPlayer.isBlackjack());
    }
}
