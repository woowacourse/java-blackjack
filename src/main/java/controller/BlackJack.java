package controller;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import domain.result.WinLossResult;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static view.InputView.getPlayerNamesInput;
import static view.InputView.getYnInput;
import static view.OutputView.*;

public class BlackJack {

    private static final String DELIMITER = ",";
    private static final String YES = "y";

    private final Dealer dealer = new Dealer();

    public void play() {
        Players players = enterPlayer();
        prepareBlackJack(players);

        playersTurn(players);
        dealerTurn();

        printDealerExtraCardsCount(dealer.getName(), dealer.getExtraHandSize());
        printEveryOneCardsNamesWithTotal(players, dealer);
        printResult(players);
    }

    private Players enterPlayer() {
        String playerNamesInput = getPlayerNamesInput();
        List<String> playerNames = Arrays.asList(playerNamesInput.split(DELIMITER));
        return new Players(playerNames);
    }

    private void prepareBlackJack(final Players players) {
        dealer.shuffleDeck();
        drawTwoCardFromDeck(dealer);
        for (Player player : players.getPlayers()) {
            drawTwoCardFromDeck(player);
        }
        printDistributeResult(players, dealer);
    }

    private void drawTwoCardFromDeck(final Participant participant) {
        participant.addCard(dealer.drawCard());
        participant.addCard(dealer.drawCard());
    }

    private void playersTurn(final Players players) {
        for (Player player : players.getPlayers()) {
            playerTurn(player);
        }
    }

    private void playerTurn(final Player player) {
        playTurn(player, () -> getYnInput(player).equalsIgnoreCase(YES));
    }

    private void dealerTurn() {
        playTurn(dealer, dealer::isBelowThreshold);
    }

    private void playTurn(final Participant participant, final Supplier<Boolean> shouldDrawMore) {
        boolean isAlive = participant.resolveBust();
        while (isAlive && shouldDrawMore.get()) {
            isAlive = drawAdditionalCard(participant);
        }
        if (!isAlive) {
            participant.setHandTotalToZero();
            printBust();
        }
    }

    private boolean drawAdditionalCard(final Participant participant) {
        participant.addCard(dealer.drawCard());
        if (participant.getClass().equals(Player.class)) {
            printHandCardsNames(participant.getName(), participant.getHand());
        }
        return participant.resolveBust();
    }

    private void printResult(final Players players) {
        Map<String, WinLossResult> playerResults = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            playerResults.put(player.getName(), WinLossResult.of(player.getWinLoss(dealer.getHandTotal())));
        }

        printAllResult(playerResults, dealer.getName());
    }
}
