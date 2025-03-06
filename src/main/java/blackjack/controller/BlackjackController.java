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
        // 1번
        gameManager.addGamblers(readAndParseNames());
        Players players = gameManager.getPlayers();
        outputView.printInitCards(players);

        // 2번
        List<Player> gamblers = players.getGamblers();
        for (Player gambler : gamblers) {
            while (!gameManager.isPlayerBust(gambler) && inputView.readOneMoreDealCard(gambler)) {
                gameManager.dealAddCard(gambler);
                outputView.printCardsMessage(gambler);
            }
        }

        // 3번
        if (gameManager.isDealerHitThenDealAddCard()) {
            outputView.printDealerHitAndDealCard();
        }

        // 4번
        outputView.printTotalCardsMessage(players);
        outputView.printGameResults(players, players.getGameResult());
    }

    private List<Player> readAndParseNames() {
        String playerNamesInput = inputView.readPlayerNames();
        List<String> playerNames = List.of(playerNamesInput.split(","));

        return playerNames.stream()
                .map(Player::new)
                .toList();
    }
}
