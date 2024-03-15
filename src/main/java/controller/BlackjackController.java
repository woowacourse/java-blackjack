package controller;

import domain.blackjackgame.BlackjackGame;
import domain.blackjackgame.GameResult;
import domain.card.CardFactory;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.Collections;
import ui.InputView;
import ui.OutputView;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        Participants participants = Participants.createPlayers(inputView.readPlayerNames());
        BlackjackGame blackjackGame = new BlackjackGame(CardFactory.createCardDeck(), Collections::shuffle);
        GameResult gameResult = playBlackjackGame(participants, blackjackGame);
        outputView.printGameResult(gameResult);
    }

    private GameResult playBlackjackGame(Participants participants, BlackjackGame blackjackGame) {
        blackjackGame.initGame(participants);
        outputView.printInitialCards(participants);

        for (Player player : participants.getPlayers()) {
            dealToPlayer(player, blackjackGame);
        }
        dealToDealer(participants.getDealer(), blackjackGame);
        outputView.printCardsWithScore(participants);
        return blackjackGame.createGameResult(participants);
    }

    private void dealToPlayer(Player player, BlackjackGame blackjackGame) {
        while (player.isNotFinished() && inputView.askForMoreCard(player.getName())) {
            blackjackGame.dealCardTo(player);
            outputView.printAllCards(player);
        }
    }

    private void dealToDealer(Dealer dealer, BlackjackGame blackjackGame) {
        while (dealer.isNotFinished()) {
            blackjackGame.dealCardTo(dealer);
            outputView.printDealerReceiveCardMessage();
        }
    }
}
