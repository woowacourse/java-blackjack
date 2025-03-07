package controller;

import static controller.dto.WinLossCountDto.computeWinLoss;
import static view.InputView.getPlayerNamesInput;
import static view.InputView.getYnInput;
import static view.OutputView.printBust;
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

    private static final String DELIMITER = ",";
    private static final String YES = "y";
    private static final String NO = "n";
    private static final String MAX_PLAYER_EXCEPTION = "플레이어는 5명까지만 입력해주세요.";
    private static final int MAX_PLAYER_SIZE = 5;

    private final List<Player> players = new ArrayList<>();
    private final Dealer dealer = new Dealer();
    private final Deck deck = new Deck();

    public void play() {
        String playerNamesInput = getPlayerNamesInput();
        enterPlayer(playerNamesInput);
        distributeCards(deck);
        printDistributeResult(players, dealer);
        playersTurn();
        dealersTurn(dealer);
        printDealerExtraCardsCount(dealer);
        printEveryOneCardsNamesWithTotal(players, dealer);
        printResult(players, dealer, computeWinLoss(players, dealer));
    }


    private void playersTurn() {
        for (Player player : players) {
            askPlayersChoice(player);
        }
    }

    private void askPlayersChoice(Player player) {
        boolean isAlive = resolveBust(player);
        while (isAlive) {
            String ynInput = getYnInput(player);
            if (ynInput.equalsIgnoreCase(YES)) {
                isAlive = drawOneMoreCard(player, new StringBuilder());
            }
            if (ynInput.equalsIgnoreCase(NO)) {
                return;
            }
        }
        printBust();
        player.setHandTotalToZero();
    }

    private boolean drawOneMoreCard(Player player, StringBuilder stringBuilder) {
        player.addCard(deck.draw());
        if (player.getClass().equals(Player.class)) {
            printHandCardsNames(player, stringBuilder);
        }
        return resolveBust(player);
    }

    public boolean resolveBust(Player player) {
        if (player.isHandBust() && player.containsOriginalAce()) {
            player.setOriginalAceValueToOne();
            resolveBust(player);
        }
        return !player.isHandBust();
    }

    public void dealersTurn(Dealer dealer) {
        boolean isAlive = resolveBust(dealer);
        while (isAlive) {
            if (dealer.isBelowThreshold()) {
                isAlive = drawOneMoreCard(dealer, new StringBuilder());
            }
            if (isAlive && !dealer.isBelowThreshold()) {
                return;
            }
        }
        dealer.setHandTotalToZero();
        printBust();
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
        if (playerNames.size() > MAX_PLAYER_SIZE) {
            throw new IllegalArgumentException(MAX_PLAYER_EXCEPTION);
        }
    }

    private void drawTwoCardFromDeck(Player player, Deck deck) {
        player.addCard(deck.draw());
        player.addCard(deck.draw());
    }
}
