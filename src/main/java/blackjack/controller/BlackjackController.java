package blackjack.controller;

import blackjack.domain.GameManager;
import blackjack.domain.card.CardPack;
import blackjack.domain.card.RandomBlackjackShuffle;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.util.PlayerNameParser;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        GameManager gameManager = createGameManager();
        Players players = gameManager.getPlayers();

        dealMoreCards(gameManager, players);
        dealMoreDealerCards(gameManager);
        displayGameResults(players);
    }

    private GameManager createGameManager() {
        CardPack cardPack = new CardPack(new RandomBlackjackShuffle());
        List<Player> readPlayers = readAndParseNames();

        Players players = new Players(readPlayers, cardPack);
        outputView.printInitCards(players);

        return new GameManager(cardPack, players);
    }

    private List<Player> readAndParseNames() {
        String playerNamesInput = inputView.readPlayerNames();
        return PlayerNameParser.parseNames(playerNamesInput);
    }

    private void dealMoreCards(final GameManager gameManager, final Players players) {
        List<Player> gamblers = players.getGamblers();
        for (Player gambler : gamblers) {
            while (!gameManager.isPlayerBust(gambler) && inputView.readOneMoreDealCard(gambler)) {
                gameManager.dealAddCard(gambler);
                outputView.printCardsMessage(gambler);
            }
        }
    }

    private void dealMoreDealerCards(final GameManager gameManager) {
        while (gameManager.isDealerHitThenDealAddCard()) {
            outputView.printDealerHitAndDealCard();
        }
    }

    private void displayGameResults(final Players players) {
        outputView.printTotalCardsMessage(players);
        outputView.printGameResults(players, players.getGameResult());
    }
}
