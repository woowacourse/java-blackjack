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

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void init(final Deck deck) {
        final Participants participants = getParticipants(deck);
        final Dealer dealer = new Dealer();
        divideFirstCard(deck, participants);

        outputView.printFirstCardStatus(participants);

        divideCard(deck, participants, dealer);
        outputView.printScoreBoard(participants);

        outputView.printFinalResult(participants);
    }

    private Participants getParticipants(final Deck deck) {
        return Participants.from(Arrays.asList(inputView.getPlayersName().split(",")));
    }

    private void divideFirstCard(final Deck deck, final Participants participants) {
        outputView.printDivideTwoCard(participants.getPlayers());
        participants.receiveInitialCards(deck);
    }

    private void divideCard(final Deck deck, final Participants participants, final Dealer dealer) {
        divideCardForPlayer(deck, participants);
        divideCardForDealer(deck, dealer);
    }

    private void divideCardForPlayer(final Deck deck, final Participants participants) {
        for (Player player : participants.getPlayers()) {
            receiveCardForPlayer(deck, player);
        }
    }

    private void receiveCardForPlayer(final Deck deck, final Player player) {
        while (player.canReceiveCard() && isInputEqualsReceiveCardCommand(player)) {
            player.receiveCard(deck.pick());
            outputView.printPlayerCardStatus(player);
        }
    }

    private boolean isInputEqualsReceiveCardCommand(final Player player) {
        return RECEIVE_CARD_COMMAND.equals(inputView.getPlayerInputGetMoreCard(player.getName()));
    }

    private void divideCardForDealer(final Deck deck, final Dealer dealer) {
        if (dealer.canReceiveCard()) {
            dealer.receiveCard(deck.pick());
            outputView.printReceiveCardForDealer();
        }
    }
}
