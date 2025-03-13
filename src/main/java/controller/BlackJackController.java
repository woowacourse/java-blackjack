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

public class BlackJackController {

    private static final String DELIMITER = ",";
    private static final String YES = "y";

    private final Dealer dealer = new Dealer();

    public void play() {
        try {
            Players players = enterPlayer();
            Players betPlayers = betMoneyPlayers(players);

            distributeCards(betPlayers);

            playersTurn(betPlayers);
            dealerTurn();

            printDealerExtraCardsCount(dealer.getName(), dealer.getExtraHandSize());
            printEveryOneCardsNamesWithTotal(betPlayers, dealer);
            printResult(betPlayers);
        } catch (IllegalArgumentException e) {
            printExceptionMessage(e);
        }
    }

    private Players enterPlayer() {
        String playerNamesInput = getPlayerNamesInput();
        List<String> playerNames = Arrays.asList(playerNamesInput.split(DELIMITER));

        List<Player> players = playerNames.stream()
                .map(playerName -> new Player(playerName.trim()))
                .toList();
        return new Players(players);
    }

    private Players betMoneyPlayers(final Players players) {
        List<Player> betPlayers = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            betPlayers.add(player.bet(getBetAmountInput(player.getName())));
        }
        return new Players(betPlayers);
    }

    private void distributeCards(final Players players) {
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
        if(participant.isMaxScore()) return;
        boolean isAlive = participant.resolveBust();
        while (isAlive && !participant.isMaxScore() && shouldDrawMore.get()) {
            isAlive = drawAdditionalCard(participant);
        }
        if (!isAlive) {
            handleBust(participant);
        }
    }

    private static void handleBust(final Participant participant) {
        participant.applyBustPenalty();
        printBust();
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
            playerResults.put(player.getName(), WinLossResult.from(dealer, player));
        }

        printAllResult(playerResults, dealer.getName());
    }
}
