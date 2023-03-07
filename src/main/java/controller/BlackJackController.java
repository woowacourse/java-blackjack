package controller;

import model.card.Deck;
import model.user.Dealer;
import model.user.Participants;
import model.user.Player;
import ui.input.ReceiveCommand;
import ui.input.InputView;
import ui.output.OutputView;

import java.util.Arrays;

import static ui.input.ReceiveCommand.HIT;

public class BlackJackController {

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
        while (player.canReceiveCard()) {
            final ReceiveCommand inputCommand = getInputMoreCardCommand(player);
            receiveCardForPlayer(deck, player, inputCommand);
        }
    }

    private ReceiveCommand getInputMoreCardCommand(final Player player) {
        while (true) {
            try {
                return ReceiveCommand.of(inputView.getPlayerInputGetMoreCard(player.getName()));
            } catch (final IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void receiveCardForPlayer(final Deck deck, final Player player, final ReceiveCommand receiveCommand) {
        if (HIT.equals(receiveCommand)) {
            player.receiveCard(deck.pick());
            outputView.printPlayerCardStatus(player);
        }
    }

    private void divideCardForDealer(final Deck deck, final Dealer dealer) {
        if (dealer.canReceiveCard()) {
            dealer.receiveCard(deck.pick());
            outputView.printReceiveCardForDealer();
        }
    }
}
