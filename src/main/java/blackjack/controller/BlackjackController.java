package blackjack.controller;

import blackjack.domain.GameManager;
import blackjack.domain.Players;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final GameManager gameManager;

    public BlackjackController(final InputView inputView, final OutputView outputView, final GameManager gameManager) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameManager = gameManager;
    }

    public void start() {
        Players players = initPlayers();
        dealMoreCards(players);
        dealMoreDealerCards(players);
        displayGameResults(players);
    }

    private void dealMoreCards(final Players players) {
        List<Gambler> gamblers = players.getGamblers();
        for (Gambler gambler : gamblers) {
            while (!gambler.isPlayerBust() && inputView.readOneMoreDealCard(gambler)) {
                gameManager.dealAddCard(gambler);
                outputView.printCardsMessage(gambler);
            }
        }
    }

    private void dealMoreDealerCards(Players players) {
        Dealer dealer = players.getDealer();
        while (gameManager.isDealerHitThenDealAddCard(dealer)) {
            outputView.printDealerHitAndDealCard();
        }
    }

    private void displayGameResults(final Players players) {
        outputView.printTotalCardsMessage(players);
        outputView.printGameResults(players, players.getGameResult());
    }

    private Players initPlayers() {
        gameManager.addGamblersAndDealInitCards(readAndParseNames());
        Players players = gameManager.getPlayers();
        outputView.printInitCards(players);
        return players;
    }

    private List<Gambler> readAndParseNames() {
        String playerNamesInput = inputView.readPlayerNames();
        return GamblerNameParse.parseNames(playerNamesInput);
    }
}
