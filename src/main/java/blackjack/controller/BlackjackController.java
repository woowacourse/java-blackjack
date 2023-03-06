package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.BlackjackResult;
import blackjack.domain.Dealer;
import blackjack.domain.Participants;
import blackjack.domain.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Participants participants = getParticipants();
        BlackjackGame blackjackGame = new BlackjackGame(participants);
        startGame(participants, blackjackGame);
        printResult(blackjackGame);
    }

    private void startGame(Participants participants, BlackjackGame blackjackGame) {
        try {
            blackjackGame.dealOutCard();
            outputView.printInitCards(participants);
            play(participants, blackjackGame);
            outputView.printCardResult(participants);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
    }

    private void printResult(BlackjackGame blackjackGame) {
        BlackjackResult result = blackjackGame.getResult();
        outputView.printGameResult(result);
    }

    private Participants getParticipants() {
        try {
            List<String> names = inputView.readNames();
            return Participants.from(names);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return getParticipants();
        }
    }

    private void play(Participants participants, BlackjackGame blackjackGame) {
        for (Player player : participants.getPlayers()) {
            playPerPlayer(player, blackjackGame);
        }
        Dealer dealer = participants.getDealer();
        playDealer(dealer, blackjackGame);
    }

    private void playPerPlayer(Player player, BlackjackGame blackjackGame) {
        GameCommand command = GameCommand.PLAY;
        while (player.isDrawable() && command.isPlay()) {
            command = getGameCommand(player);
            giveCard(player, blackjackGame, command);
            outputView.printPlayerCards(player);
        }
    }

    private GameCommand getGameCommand(Player player) {
        try {
            String inputCommand = inputView.readIsContinue(player.getName());
            return GameCommand.from(inputCommand);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return getGameCommand(player);
        }
    }

    private void giveCard(Player player, BlackjackGame blackjackGame, GameCommand command) {
        if (command.isPlay()) {
            blackjackGame.drawCard(player);
        }
    }

    private void playDealer(Dealer dealer, BlackjackGame blackjackGame) {
        while (dealer.isDrawable()) {
            outputView.printDealerState();
            blackjackGame.drawCard(dealer);
        }
    }
}
