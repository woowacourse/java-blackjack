package controller;

import static controller.dto.WinLossCountDto.computeWinLoss;
import static view.InputView.getPlayerNamesInput;
import static view.InputView.getYnInput;
import static view.OutputView.*;

import domain.participant.Dealer;
import domain.participant.Player;

import domain.participant.Players;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlackJack {

    private static final String DELIMITER = ",";
    private static final String YES = "y";
    private static final String NO = "n";

    private final Dealer dealer = new Dealer();

    public void play() {
        Players players = enterPlayer();
        prepareBlackJack(players);
        printDistributeResult(players, dealer);
        playersTurn(players);

        dealersTurn(dealer);
        printDealerExtraCardsCount(dealer);
        printEveryOneCardsNamesWithTotal(players, dealer);
        printResult(players, dealer, computeWinLoss(players, dealer));
    }

    private Players enterPlayer() {
        String playerNamesInput = getPlayerNamesInput();
        List<String> playerNames = Arrays.asList(playerNamesInput.split(DELIMITER));
        return new Players(playerNames);
    }

    private void prepareBlackJack(Players players) {
        dealer.shuffleDeck();
        drawTwoCardFromDeck(dealer);
        for (Player player : players.getPlayers()) {
            drawTwoCardFromDeck(player);
        }
    }

    public void dealersTurn(Dealer dealer) {
        boolean isAlive = resolveBust(dealer);
        while (isAlive) {
            if (dealer.isBelowThreshold()) {
                isAlive = drawAdditionalCard(dealer, new StringBuilder());
            }
            if (isAlive && !dealer.isBelowThreshold()) {
                return;
            }
        }
        dealer.setHandTotalToZero();
        printBust();
    }

    private void playersTurn(Players players) {
        for (Player player : players.getPlayers()) {
            askPlayersChoice(player);
        }
    }

    private void askPlayersChoice(Player player) {
        boolean isAlive = resolveBust(player);
        while (isAlive) {
            String ynInput = getYnInput(player);
            if (ynInput.equalsIgnoreCase(YES)) {
                isAlive = drawAdditionalCard(player, new StringBuilder());
            }
            if (ynInput.equalsIgnoreCase(NO)) {
                return;
            }
        }
        player.setHandTotalToZero();
        printBust();
    }

    public boolean resolveBust(Player player) {
        if (player.isHandBust() && player.containsOriginalAce()) {
            player.setOriginalAceValueToOne();
            resolveBust(player);
        }
        return !player.isHandBust();
    }

    private void drawTwoCardFromDeck(Player player) {
        player.addCard(dealer.drawCard());
        player.addCard(dealer.drawCard());
    }

    private boolean drawAdditionalCard(Player player, StringBuilder stringBuilder) {
        player.addCard(dealer.drawCard());
        if (player.getClass().equals(Player.class)) {
            printHandCardsNames(player, stringBuilder);
        }
        return resolveBust(player);
    }
}
