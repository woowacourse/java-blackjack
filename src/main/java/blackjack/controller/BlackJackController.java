package blackjack.controller;

import blackjack.domain.game.BlackJackGame;
import blackjack.domain.game.GameResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackJackController {

    public void run() {
        BlackJackGame blackJackGame = createBlackJackGame();
        Dealer dealer = blackJackGame.getDealer();
        Players players = blackJackGame.getPlayers();
        OutputView.printInitialCards(dealer, players);

        playAllPlayers(blackJackGame);
        playDealer(blackJackGame);

        showGameResult(blackJackGame, dealer, players);
    }

    private BlackJackGame createBlackJackGame() {
        try {
            List<Name> playerNames = InputView.inputPlayerNames();
            return new BlackJackGame(playerNames);
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return createBlackJackGame();
        }
    }

    private void playAllPlayers(BlackJackGame blackJackGame) {
        while (!blackJackGame.isAllPlayersFinished()) {
            playPresentPlayer(blackJackGame);
            blackJackGame.passToNextPlayer();
        }
    }

    private void playPresentPlayer(BlackJackGame blackJackGame) {
        while (!blackJackGame.isPresentPlayerFinished()) {
            blackJackGame.drawPresentPlayer(wantDraw(blackJackGame.getPresentPlayer()));
            OutputView.printCards(blackJackGame.getPresentPlayer());
        }
    }

    private boolean wantDraw(Player player) {
        try {
            return InputView.inputWantDraw(player.getName());
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return wantDraw(player);
        }
    }

    private void playDealer(BlackJackGame blackJackGame) {
        while (!blackJackGame.isDealerFinished()) {
            OutputView.printDealerDrawInfo();
            blackJackGame.drawDealer();
        }
    }

    private void showGameResult(BlackJackGame blackJackGame, Dealer dealer, Players players) {
        GameResult gameResult = blackJackGame.createGameResult();
        OutputView.printCardsResult(dealer, players);
        OutputView.printGameResult(gameResult);
    }
}
