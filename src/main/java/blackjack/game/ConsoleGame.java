package blackjack.game;

import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.GameResult;
import blackjack.view.Command;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import java.util.List;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class ConsoleGame {

    public void run() {
        BlackjackGame blackjackGame = createBlackjackGame();

        Participants participants = blackjackGame.getParticipants();
        Participant dealer = participants.findDealer();
        List<Participant> players = participants.findPlayers();

        OutputView.printInitialCards(participants.findDealer(), participants.findPlayers());

        playPlayersTurn(blackjackGame, players);
        playDealerTurn(blackjackGame, dealer);

        showGameResult(blackjackGame, dealer, players);
    }

    private BlackjackGame createBlackjackGame() {
        try {
            List<Name> playerNames = InputView.inputPlayerNames();
            return new BlackjackGame(playerNames);
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return createBlackjackGame();
        }
    }

    private void playPlayersTurn(BlackjackGame blackjackGame, List<Participant> players) {
        for (Participant player : players) {
            playPlayerTurn(blackjackGame, player);
        }
    }

    private void playPlayerTurn(BlackjackGame blackjackGame, Participant player) {
        while (!player.isFinished() && !inputCommand(player).isStay()) {
            blackjackGame.drawCard(player);
            OutputView.printCards(player);
        }
    }

    private Command inputCommand(Participant player) {
        try {
            return InputView.inputWantDraw(player.getName());

        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return inputCommand(player);
        }
    }

    private void playDealerTurn(BlackjackGame blackjackGame, Participant dealer) {
        while (!dealer.isFinished()) {
            OutputView.printDealerDrawInfo();
            blackjackGame.drawCard(dealer);
        }
    }

    private void showGameResult(BlackjackGame blackjackGame, Participant dealer, List<Participant> players) {
        GameResult gameResult = blackjackGame.createGameResult();
        OutputView.printCardsResult(dealer, players);
        OutputView.printGameResult(gameResult);
    }

}
