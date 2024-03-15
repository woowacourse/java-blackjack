package controller;

import domain.blackjackgame.BlackjackGame;
import domain.blackjackgame.GameResult;
import domain.blackjackgame.ResultStatus;
import domain.card.CardFactory;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
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
        Dealer dealer = new Dealer();
        Players players = new Players(inputView.readPlayerNames());
        BlackjackGame blackjackGame = new BlackjackGame(CardFactory.createCardDeck(), Collections::shuffle);

        GameResult gameResult = playBlackjackGame(dealer, players, blackjackGame);
        showGameResult(gameResult);
    }

    private GameResult playBlackjackGame(Dealer dealer, Players players, BlackjackGame blackjackGame) {
        blackjackGame.initGame(dealer, players);
        outputView.printCards(dealer, players);

        for (Player player : players.getPlayers()) {
            dealToPlayer(player, blackjackGame);
        }
        dealToDealer(dealer, blackjackGame);
        outputView.printCardsWithScore(dealer, players);
        return blackjackGame.createGameResult(dealer, players);
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
