package controller;

import static controller.dto.WinLossCountDto.computeWinLoss;
import static view.InputView.getPlayerNamesInput;
import static view.InputView.getYnInput;
import static view.OutputView.*;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;

import domain.participant.Players;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class BlackJack {

    private static final String DELIMITER = ",";
    private static final String YES = "y";
    private static final String NO = "n";

    private final Dealer dealer = new Dealer();

    public void play() {
        Players players = enterPlayer();
        prepareBlackJack(players);

        playersTurn(players);
        dealerTurn();

        printDealerExtraCardsCount(dealer);
        printEveryOneCardsNamesWithTotal(players, dealer);
        printResult(players, dealer, computeWinLoss(players, dealer));
    }

    public boolean resolveBust(final Participant participant) {
        if (participant.isHandBust() && participant.containsOriginalAce()) {
            participant.setOriginalAceValueToOne();
            resolveBust(participant);
        }
        return !participant.isHandBust();
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
        boolean isAlive = resolveBust(participant);
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
            printHandCardsNames(participant);
        }
        return resolveBust(participant);
    }
}
