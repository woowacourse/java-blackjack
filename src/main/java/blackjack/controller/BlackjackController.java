package blackjack.controller;

import blackjack.domain.card.CardPack;
import blackjack.domain.card.Cards;
import blackjack.domain.game.GameManager;
import blackjack.domain.player.BetAmount;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.PlayerName;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void start() {
        List<String> gamblerNames = inputView.readPlayerNames();
        List<Gambler> gamblers = getGamblers(gamblerNames);
        Dealer dealer = new Dealer(new Cards());
        GameManager gameManager = new GameManager(CardPack.createShuffled(), new Players(dealer, gamblers));
        initPlayers(gameManager);
        dealMoreCards(gameManager);
        dealMoreDealerCards(gameManager);
        displayGameResults(gameManager);
    }

    private List<Gambler> getGamblers(List<String> gamblerNames) {
        Map<PlayerName, BetAmount> gamblersBetAmount = getGamblersBetAmount(gamblerNames);
        List<Gambler> gamblers = new ArrayList<>();
        for (Map.Entry<PlayerName, BetAmount> entry : gamblersBetAmount.entrySet()) {
            gamblers.add(new Gambler(entry.getKey(), entry.getValue(), new Cards()));
        }
        return gamblers;
    }

    private Map<PlayerName, BetAmount> getGamblersBetAmount(List<String> gamblerNames) {
        Map<PlayerName, BetAmount> betAmounts = new HashMap<>();
        for (String gamblerName : gamblerNames) {
            int amount = Integer.parseInt(inputView.readBetAmount(gamblerName));
            PlayerName playerName = new PlayerName(gamblerName);
            BetAmount betAmount = new BetAmount(amount);
            betAmounts.put(playerName, betAmount);
        }
        return betAmounts;
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
            dealCardByInputs(gameManager, gambler);
        }
    }

    private void dealCardByInputs(GameManager gameManager, Gambler gambler) {
        while (gambler.isPlayerNotBust()) {
            if (wantsNoMoreCard(gambler)) {
                return;
            }
            gameManager.dealAddCard(gambler);
            outputView.printCardsMessage(gambler);
        }
    }

    private boolean wantsNoMoreCard(Gambler gambler) {
        return !inputView.readOneMoreDealCard(gambler);
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
