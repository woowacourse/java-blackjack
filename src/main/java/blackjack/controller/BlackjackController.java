package blackjack.controller;

import blackjack.domain.GameManager;
import blackjack.domain.Players;
import blackjack.domain.player.Player;
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
        dealMoreDealerCards();
        displayGameResults(players);
    }

    private void dealMoreCards(final Players players) {
        List<Player> gamblers = players.getGamblers();
        for (Player gambler : gamblers) {
            while (!gameManager.isPlayerBust(gambler) && inputView.readOneMoreDealCard(gambler)) {
                gameManager.dealAddCard(gambler);
                outputView.printCardsMessage(gambler);
            }
        }
    }

    private void dealMoreDealerCards() {
        while (gameManager.isDealerHitThenDealAddCard()) {
            outputView.printDealerHitAndDealCard();
        }
    }

    private void displayGameResults(final Players players) {
        outputView.printTotalCardsMessage(players);
        outputView.printGameResults(players, players.getGameResult());
    }

    private Players initPlayers() {
        gameManager.addGamblers(readAndParseNames());
        Players players = gameManager.getPlayers();
        outputView.printInitCards(players);
        return players;
    }

    private List<Player> readAndParseNames() {
        String playerNamesInput = inputView.readPlayerNames();
        return PlayerNameParser.parseNames(playerNamesInput);
    }
}
