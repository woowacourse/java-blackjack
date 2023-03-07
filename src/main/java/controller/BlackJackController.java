package controller;

import model.card.Deck;
import model.user.Dealer;
import model.user.Participants;
import model.user.Player;
import ui.input.InputView;
import ui.output.OutputView;

import java.util.Arrays;

public class BlackJackController {

    private static final String RECEIVE_CARD_COMMAND = "y";

    public void init(final Deck deck) {
        final Participants participants = getParticipants(deck);
        final Dealer dealer = new Dealer();
        divideFirstCard(deck, participants);

        OutputView.printFirstCardStatus(participants);
        divideCard(deck, participants);
        divideCardForDealer(deck, dealer);

        OutputView.printScoreBoard(participants);

        OutputView.printFinalResult(participants);
    }

    private Participants getParticipants(final Deck deck) {
        return Participants.from(Arrays.asList(InputView.getPlayersName().split(",")));
    }

    private static void divideFirstCard(final Deck deck, final Participants participants) {
        OutputView.printDivideTwoCard(participants.getPlayers());
        participants.receiveInitialCards(deck);
    }

    private static void divideCard(final Deck deck, final Participants participants) {
        for (Player player : participants.getPlayers()) {
            receiveCardForPlayer(deck, player);
        }
    }

    private static void receiveCardForPlayer(final Deck deck, final Player player) {
        while (canReceiveCard(player)) {
            player.receiveCard(deck.pick());
            OutputView.printPlayerCardStatus(player);
        }
    }

    private static boolean canReceiveCard(final Player player) {
        return player.canReceiveCard() && isInputEqualsReceiveCardCommand(player);
    }

    private static void divideCardForDealer(final Deck deck, final Dealer dealer) {
        if (dealer.canReceiveCard()) {
            dealer.receiveCard(deck.pick());
            OutputView.printReceiveCardForDealer();
        }
    }

    private static boolean isInputEqualsReceiveCardCommand(final Player player) {
        return RECEIVE_CARD_COMMAND.equals(InputView.getPlayerInputGetMoreCard(player.getName()));
    }
}
