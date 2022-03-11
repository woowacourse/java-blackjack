package blackjack.controller;

import blackjack.domain.game.BlackJackGame;
import blackjack.domain.game.GameResult;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackJackController {

    public void run() {
        BlackJackGame blackJackGame = createBlackJackGame();

        Participant dealer = blackJackGame.findDealer();
        List<Participant> players = blackJackGame.findPlayers();

        OutputView.printInitialCards(dealer, players);

        playPlayersTurn(blackJackGame, players);
        playDealerTurn(blackJackGame, dealer);

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

    private void playPlayersTurn(BlackJackGame blackJackGame, List<Participant> players) {
        for (Participant player : players) {
            playPlayerTurn(blackJackGame, player);
        }
    }

    private void playPlayerTurn(BlackJackGame blackJackGame, Participant player) {
        boolean draw = true;

        while (draw && !player.isFinished()) {
            draw = wantDraw(player);
            drawPlayerCard(blackJackGame, player, draw);
            OutputView.printCards(player);
        }
    }

    private void drawPlayerCard(BlackJackGame blackJackGame, Participant player, boolean draw) {
        if (draw) {
            blackJackGame.drawPlayerCard(player);
        }
    }

    private boolean wantDraw(Participant player) {
        try {
            return InputView.inputWantDraw(player.getName());

        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return wantDraw(player);
        }
    }

    private void playDealerTurn(BlackJackGame blackJackGame, Participant dealer) {
        while (!dealer.isFinished()) {
            OutputView.printDealerDrawInfo();
            blackJackGame.drawPlayerCard(dealer);
        }
    }

    private void showGameResult(BlackJackGame blackJackGame, Participant dealer, List<Participant> players) {
        GameResult gameResult = blackJackGame.createGameResult();
        OutputView.printCardsResult(dealer, players);
        OutputView.printGameResult(gameResult);
    }

}
