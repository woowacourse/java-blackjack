package blackjack.controller;

import blackjack.domain.GameManager;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    private static final String PLAYER_NAME_DELIMITER = ",";

    private final InputView inputView;
    private final OutputView outputView;
    private final GameManagerFactory gameManagerFactory;

    public BlackjackController(final InputView inputView, final OutputView outputView, final GameManagerFactory gameManagerFactory) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameManagerFactory = gameManagerFactory;
    }

    public void start() {
        List<String> gamblerNames = readAndParseNames();
        Map<String, Integer> betAmounts = readBetAmounts(gamblerNames);
        GameManager gameManager = gameManagerFactory.createGameManager(betAmounts);

        outputView.printInitCardsToPlayers(gameManager.getPlayers());

        Players players = gameManager.getPlayers();

        dealMoreCards(gameManager, players);
        dealMoreDealerCards(gameManager);
        displayGameResults(gameManager);
    }

    private List<String> readAndParseNames() {
        String gamblerNames = inputView.readGamblerNames();
        return List.of(gamblerNames.split(PLAYER_NAME_DELIMITER));
    }

    private Map<String, Integer> readBetAmounts(List<String> gamblerNames) {
        return gamblerNames.stream()
                .collect(Collectors.toMap(
                        gambler -> gambler,
                        inputView::readBetAmount
                ));
    }

    private void dealMoreCards(final GameManager gameManager, final Players players) {
        List<Gambler> gamblers = players.getGamblers();
        for (Gambler gambler : gamblers) {
            while (!gameManager.isPlayerBust(gambler) && inputView.readOneMoreDealCard(gambler)) {
                gameManager.addCardForGambler(gambler);
                outputView.printCardsToPlayer(gambler);
                outputView.println();
            }
        }
    }

    private void dealMoreDealerCards(final GameManager gameManager) {
        while (gameManager.isDealerHitThenAddCard()) {
            outputView.printDealerHitAndDealCard();
        }
    }

    private void displayGameResults(final GameManager gameManager) {
        outputView.printResultCardsToPlayers(gameManager.getPlayers());
        outputView.printGameResults(gameManager.getGameResult());
    }
}
