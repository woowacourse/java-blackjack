package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Map;

public class BlackJackController {

    public void run() {
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.makeNames(InputView.requestPlayers());
        blackjackGame.makePlayers(InputView.requestMoney(blackjackGame.getNames()));
        OutputView.showInitiate(blackjackGame.getDealer(), blackjackGame.getPlayers());

        play(blackjackGame);

        end(blackjackGame);
    }

    private void play(BlackjackGame blackjackGame) {
        for (Player player : blackjackGame.getPlayersList()) {
            processPlayer(blackjackGame, player);
        }
        processDealer(blackjackGame, blackjackGame.getDealer());
    }

    private void processPlayer(BlackjackGame blackjackGame, Player player) {
        boolean playerSayYes = true;
        while (!player.isFinished() && playerSayYes) {
            String input = InputView.requestMoreDraw(player.getName());
            playerSayYes = blackjackGame.isPlayerDraw(player, input);
            OutputView.showPlayerCard(player);
        }
    }

    private void processDealer(BlackjackGame blackjackGame, Dealer dealer) {
        if (!dealer.isFinished()) {
            blackjackGame.dealerDraw();
            OutputView.showDealerDraw();
            processDealer(blackjackGame, dealer);
        }
    }

    private void end(BlackjackGame blackjackGame) {
        OutputView.showScoreResult(blackjackGame.getDealer(), blackjackGame.getPlayers());

        Map<Player, Double> playerEarning = blackjackGame.playerEarningResult();
        double dealerEarning = blackjackGame.getDealerEarning(playerEarning);

        OutputView.showEarning(playerEarning);
        OutputView.showEarning(dealerEarning * -1);
    }
}
