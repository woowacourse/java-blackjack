package blackjack.controller;

import blackjack.domain.Blackjack;
import blackjack.domain.common.Names;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.GamePlayer;
import blackjack.domain.result.Result;
import blackjack.view.BlackjackCommand;
import blackjack.view.InputView;
import blackjack.view.PlayerView;
import blackjack.view.ResultView;

public class BlackjackController {
    private final Blackjack blackjack;

    public BlackjackController(final Blackjack blackjack) {
        this.blackjack = blackjack;
    }

    public void playBlackJack() {
        joinPlayer();

        processGame();

        checkGameResult();
    }

    private void joinPlayer() {
        final Names names = Names.from(InputView.inputPlayerNames());
        blackjack.acceptPlayers(names);
        PlayerView.printPlayers(blackjack.getDealer(), blackjack.getGamePlayers());
    }

    private void processGame() {
        processGamePlayers();
        processDealer();
        PlayerView.printPlayersWithScore(blackjack.getDealer(), blackjack.getGamePlayers());
    }

    private void processGamePlayers() {
        blackjack.getGamePlayers()
                 .forEach(this::processGamePlayer);
    }

    private void processGamePlayer(final GamePlayer gamePlayer) {
        while (gamePlayer.isReceivable() && isHit(gamePlayer)) {
            blackjack.participantHitCard(gamePlayer);
            PlayerView.printGamePlayer(gamePlayer);
        }
    }

    private void checkGameResult() {
        final Result result = blackjack.checkPlayersResult();
        ResultView.printResult(result);
    }

    private void processDealer() {
        final Dealer dealer = blackjack.getDealer();
        if (dealer.isReceivable()) {
            PlayerView.printDealerDrawMessage();
            blackjack.participantHitCard(dealer);
            return;
        }
        PlayerView.printDealerNotDrawMessage();
    }

    private boolean isHit(final GamePlayer gamePlayer) {
        final BlackjackCommand command = InputView.inputBlackjackCommand(gamePlayer.getNameAsString());
        return command.isHit();
    }
}
