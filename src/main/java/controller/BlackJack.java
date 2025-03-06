package controller;

import static domain.WinLossCountResult.computeWinLoss;
import static view.InputView.getPlayerNamesInput;
import static view.InputView.getYnInput;
import static view.OutputView.printDealerExtraCardsCount;
import static view.OutputView.printDistributeResult;
import static view.OutputView.printEveryOneCardsNamesWithTotal;
import static view.OutputView.printHandCardsNames;
import static view.OutputView.printResult;

import domain.Dealer;
import domain.Deck;
import domain.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlackJack {

    public static final String DELIMITER = ",";

    private final List<Player> players = new ArrayList<>();
    private final Dealer dealer = new Dealer();
    private final Deck deck = new Deck();

    public void play() {
        String playerNamesInput = getPlayerNamesInput();
        enterPlayer(playerNamesInput);
        distributeCards(deck);
        printDistributeResult(players, dealer);
        playersTurn();

        int dealerExtraCardsCount = dealersTurn(dealer);
        printDealerExtraCardsCount(dealer, dealerExtraCardsCount);
        printEveryOneCardsNamesWithTotal(players, dealer);
        printResult(players, dealer, computeWinLoss(players, dealer));
    }


    public int dealersTurn(Dealer dealer) {
        int count = 0;
        while (true) {
            if (dealer.isBelowThreshold()) {
                drawOneMoreCard(dealer, new StringBuilder());
            }
            if (!dealer.isBelowThreshold()) {
                return count;
            }
            count++;
        }
    }

    public boolean resolveBust(Player player) {
        if (player.isHandBust() && player.containsOriginalAce()) {
            player.setOriginalAceValueToOne();
            resolveBust(player);
        }
        return !player.isHandBust();
    }

    private void playersTurn() {
        for (Player player : players) {
            askPlayersChoice(player);
        }
    }

    private void askPlayersChoice(Player player) {
        while (true) {
            String ynInput = getYnInput(player);
            if (ynInput.equalsIgnoreCase("y")) {
                drawOneMoreCard(player, new StringBuilder());
            }
            if (ynInput.equalsIgnoreCase("n")) {
                return;
            }
        }
    }


    private void drawOneMoreCard(Player player, StringBuilder stringBuilder) {
        player.addCard(deck.draw());
        if (player.getClass().equals(Player.class)) {
            printHandCardsNames(player, stringBuilder);
        }
        resolveBust(player);
    }

    private void distributeCards(Deck deck) {
        deck.shuffle();
        drawTwoCardFromDeck(dealer, deck);
        for (Player player : players) {
            drawTwoCardFromDeck(player, deck);
        }
    }

    private void enterPlayer(String playersInput) {
        List<String> playerNames = Arrays.asList(playersInput.split(DELIMITER));
        validateMaxPlayer(playerNames);
        for (String playerName : playerNames) {
            players.add(new Player(playerName.trim()));
        }
    }

    private void validateMaxPlayer(List<String> playerNames) {
        if (playerNames.size() > 5) {
            throw new IllegalArgumentException("플레이어는 5명까지만 입력해주세요.");
        }
    }

    private void drawTwoCardFromDeck(Player player, Deck deck) {
        player.addCard(deck.draw());
        player.addCard(deck.draw());
    }
}
