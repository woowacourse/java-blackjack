package controller;

import domain.BlackjackGame;
import domain.Dealer;
import domain.Participants;
import domain.Player;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> names = inputView.readNames();
        Participants participants = Participants.from(names);
        BlackjackGame blackjackGame = new BlackjackGame(participants);
        blackjackGame.dealOutCard();
        outputView.printInitCards(participants);
        play(participants, blackjackGame);
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
        while (!player.isBust() && command.isPlay()) {
            String inputCommand = inputView.readIsContinue(player.getName());
            command = GameCommand.from(inputCommand);
            giveCard(player, blackjackGame, command);
            outputView.printPlayerCards(player);
        }
    }

    private void giveCard(Player player, BlackjackGame blackjackGame, GameCommand command) {
        if (command.isPlay()) {
            blackjackGame.giveCard(player);
        }
    }

    private void playDealer(Dealer dealer, BlackjackGame blackjackGame) {
        while (dealer.isHit()) {
            outputView.printDealerState();
            blackjackGame.giveCard(dealer);
        }
    }
}
