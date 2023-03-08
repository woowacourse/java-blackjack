package controller;

import model.card.Deck;
import model.user.Dealer;
import model.user.Participants;
import model.user.Player;
import ui.input.InputView;
import ui.input.ReceiveCommand;
import ui.output.OutputView;

import java.util.Arrays;

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

        outputView.printFirstCardStatus(BlackJackGameResponse.create(participants));

        divideCard(deck, participants, dealer);
        outputView.printScoreBoard(BlackJackGameResponse.create(participants));

        outputView.printFinalResult(BlackJackGameResponse.create(participants));
    }

    private Participants getParticipants(final Deck deck) {
        return Participants.from(Arrays.asList(inputView.getPlayersName().split(",")));
    }

    private void divideFirstCard(final Deck deck, final Participants participants) {
        outputView.printDivideTwoCard(BlackJackGameResponse.create(participants));
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
        boolean canReceive = player.canReceiveCard();
        while (canReceive) {
            final ReceiveCommand inputCommand = getInputMoreCardCommand(player);
            receiveCardForPlayer(deck, player, inputCommand);
            canReceive = canPlayerReceiveCard(player, inputCommand);
        }
    }

    private boolean canPlayerReceiveCard(final Player player, final ReceiveCommand receiveCommand) {
        return player.canReceiveCard() && ReceiveCommand.isHit(receiveCommand);
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
        if (ReceiveCommand.isHit(receiveCommand)) {
            player.receiveCard(deck.pick());
            final HandResponse handResponse = HandResponse.of(player.calculateTotalValue(), player.getHand().getCards());
            outputView.printPlayerCardStatus(new UserResponse(player.getName(), handResponse));
        }
    }

    private void divideCardForDealer(final Deck deck, final Dealer dealer) {
        if (dealer.canReceiveCard()) {
            dealer.receiveCard(deck.pick());
            outputView.printReceiveCardForDealer();
        }
    }
}
