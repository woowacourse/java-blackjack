package blackjack.controller;

import blackjack.domain.GameManager;
import blackjack.domain.card.CardPack;
import blackjack.domain.card.RandomBlackjackShuffle;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackjackController {

    private static final String PLAYER_NAME_DELIMITER = ",";

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        GameManager gameManager = createGameManager();
        outputView.printInitCardsToPlayers(gameManager.getPlayers());

        Players players = gameManager.getPlayers();

        dealMoreCards(gameManager, players);
        dealMoreDealerCards(gameManager);
        displayGameResults(players);
    }

    private GameManager createGameManager() {
        CardPack cardPack = new CardPack(new RandomBlackjackShuffle());
        List<Gambler> players = readAndParseNames();
        return new GameManager(cardPack, players);
    }

    private List<Gambler> readAndParseNames() {
        String playerNamesInput = inputView.readPlayerNames();
        return createGamblers(playerNamesInput);
    }

    private List<Gambler> createGamblers(String namesInput) {
        List<String> names = List.of(namesInput.split(PLAYER_NAME_DELIMITER));
        return names.stream()
                .map(this::createGambler)
                .collect(Collectors.toList());
    }

    private Gambler createGambler(final String name) {
        int batMoney = inputView.readBatAmount(name);
        return new Gambler(name, batMoney);
    }

    private void dealMoreCards(final GameManager gameManager, final Players players) {
        List<Gambler> gamblers = players.getGamblers();
        for (Gambler gambler : gamblers) {
            while (!gameManager.isPlayerBust(gambler) && inputView.readOneMoreDealCard(gambler)) {
                gameManager.addCardForGambler(gambler);
                outputView.printCardsToPlayer(gambler);
            }
        }
    }

    private void dealMoreDealerCards(final GameManager gameManager) {
        while (gameManager.isDealerHitThenAddCard()) {
            outputView.printDealerHitAndDealCard();
        }
    }

    private void displayGameResults(final Players players) {
        outputView.printResultCardsToPlayers(players);
//        outputView.printGameResults(players.getGameResult());
    }
}
