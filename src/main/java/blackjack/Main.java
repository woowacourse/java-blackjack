package blackjack;

import blackjack.domain.BlackjackGame;
import blackjack.domain.HitOrStand;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Main {
    public static void main(String[] args) {
        BlackjackGame blackjackGame = new BlackjackGame(Players.fromNames(InputView.inputPlayerName()));
        OutputView.printInitCard(blackjackGame.getCardStatus());
        Players players = blackjackGame.getPlayers();
        while (players.hasNext()) {
            playerHit(blackjackGame, players);
        }
        blackjackGame.dealerHit();
        OutputView.printHitResult(blackjackGame.getHitResult());
        OutputView.printResult(blackjackGame.judgeWinDrawLose());
    }

    private static void playerHit(BlackjackGame blackjackGame, Players players) {
        Player nowTurnPlayer = players.getNowPlayer();
        HitOrStand flag = HitOrStand.valueOf(InputView.inputHitOrStand(players.getNowPlayer().getName()));
        if (flag.isStand()) {
            players.next();
            return;
        }
        blackjackGame.playerHit();
        OutputView.printPresentStatus(nowTurnPlayer.getName(), blackjackGame.toListCardDto(nowTurnPlayer),
                nowTurnPlayer.getCards().calculateScore(),
                nowTurnPlayer.isBust(), nowTurnPlayer.isBlackjack());
        if (nowTurnPlayer.isBust() || nowTurnPlayer.isBlackjack()) {
            players.next();
        }
    }
}
