package controller;

import domain.blackjackgame.BlackjackGame;
import domain.blackjackgame.GameResult;
import domain.blackjackgame.ResultStatus;
import domain.card.CardFactory;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.Collections;
import java.util.Map;
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
        showGameResult(gameResult);
    }

    private GameResult playBlackjackGame(Participants participants, BlackjackGame blackjackGame) {
        blackjackGame.initGame(participants);
        outputView.printCards(participants);

        for (Player player : participants.getPlayers()) {
            dealToPlayer(player, blackjackGame);
        }
        dealToDealer(participants.getDealer(), blackjackGame);
        outputView.printCardsWithScore(participants);
        return blackjackGame.createGameResult(participants);
    }

    private void dealToPlayer(Player player, BlackjackGame blackjackGame) {
        while (inputView.askForMoreCard(player.getName())) {
            blackjackGame.dealCardTo(player);
            outputView.printCardsAfterHit(player);
        }
    }

    private void dealToDealer(Dealer dealer, BlackjackGame blackjackGame) {
        while (dealer.isNotExceedDrawPolicy()) {
            blackjackGame.dealCardTo(dealer);
            outputView.printDealerReceiveCardMessage();
        }
    }

    private void showGameResult(GameResult gameResult) {
        Map<ResultStatus, Integer> dealerResult = gameResult.getDealerResult();
        Map<Player, ResultStatus> playerResult = gameResult.getPlayerResult();

        outputView.printParticipantResult(dealerResult, playerResult);
    }
}
