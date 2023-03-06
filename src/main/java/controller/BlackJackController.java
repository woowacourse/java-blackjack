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

    private static void divideCardForDealer(Deck deck, Dealer dealer) {
        if (dealer.canReceiveCard()) {
            dealer.receiveCard(deck.pick());
            OutputView.printReceiveCardForDealer();
        }
    }

    private static void divideCard(Deck deck, Participants participants) {
        for (Player player : participants.getPlayers()) {
            receiveCardForPlayer(deck, player);
        }
    }

    private static void receiveCardForPlayer(Deck deck, Player player) {
        while (canReceiveCard(player)) {
            player.receiveCard(deck.pick());
            OutputView.printPlayerCardStatus(player);
        }
    }

    private static boolean canReceiveCard(Player player) {
        return player.canReceiveCard() && isInputEqualsReceiveCardCommand(player);
    }

    private static boolean isInputEqualsReceiveCardCommand(Player player) {
        return RECEIVE_CARD_COMMAND.equals(InputView.getPlayerInputGetMoreCard(player.getName()));
    }


    private static void divideFirstCard(Deck deck, Participants participants) {
        OutputView.printDivideTwoCard(participants.getPlayers());
        participants.receiveInitialCards(deck);
    }

    private Participants getParticipants(final Deck deck) {
        return Participants.from(Arrays.asList(InputView.getPlayersName().split(",")));
    }
}
