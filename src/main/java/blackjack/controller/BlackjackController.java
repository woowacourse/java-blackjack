package blackjack.controller;

import blackjack.domain.game.GameManager;
import blackjack.domain.player.BetAmount;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.PlayerName;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void start() {
        List<String> gamblerNames = inputView.readPlayerNames();
        List<Gambler> gamblers = getGamblersWithBetAmount(gamblerNames);
        GameManager gameManager = new GameManager(gamblers);
        initPlayers(gameManager);
        dealMoreCards(gameManager);
        dealMoreDealerCards(gameManager);
        displayGameResults(gameManager);
    }

    private List<Gambler> getGamblersWithBetAmount(List<String> gamblerNames) {
        List<Gambler> gamblers = new ArrayList<>();
        for (String gamblerName : gamblerNames) {
            int amount = Integer.parseInt(inputView.readBetAmount(gamblerName));
            BetAmount betAmount = new BetAmount(amount);
            PlayerName playerName = new PlayerName(gamblerName);
            gamblers.add(new Gambler(playerName, betAmount));
        }
        return gamblers;
    }

    private void initPlayers(GameManager gameManager) {
        gameManager.dealInitCards();
        Players players = gameManager.getPlayers();
        outputView.printInitCards(players);
    }

    private void dealMoreCards(GameManager gameManager) {
        Players players = gameManager.getPlayers();
        List<Gambler> gamblers = players.getGamblers();
        for (Gambler gambler : gamblers) {
            while (gambler.isPlayerNotBust() && inputView.readOneMoreDealCard(gambler)) {
                gameManager.dealAddCard(gambler);
                outputView.printCardsMessage(gambler);
            }
        }
    }

    private void dealMoreDealerCards(GameManager gameManager) {
        Players players = gameManager.getPlayers();
        Dealer dealer = players.getDealer();
        while (gameManager.isDealerHitThenDealAddCard(dealer)) {
            outputView.printDealerHitAndDealCard();
        }
    }

    private void displayGameResults(GameManager gameManager) {
        Players players = gameManager.getPlayers();
        outputView.printTotalCardsMessage(players);
        outputView.printGameResults(players, players.getGameResult());
    }
}
