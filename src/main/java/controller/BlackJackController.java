package controller;

import java.util.Arrays;
import model.card.Deck;
import model.money.Bet;
import model.money.Purse;
import model.user.Dealer;
import model.user.Participants;
import model.user.Player;
import ui.input.InputView;
import ui.input.ReceiveCommand;
import ui.output.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void init(final Deck deck) {
        final Dealer dealer = new Dealer();
        final Participants participants = getParticipants(dealer);
        final Purse purse = Purse.create(participants.getPlayers());

        betMoney(participants, purse);

        divideFirstCard(deck, participants);

        outputView.printFirstCardStatus(BlackJackGameResponse.create(participants));

        divideCard(deck, participants, dealer);
        outputView.printScoreBoard(BlackJackGameResponse.create(participants));
    }

    private Participants getParticipants(final Dealer dealer) {
        return Participants.of(Arrays.asList(inputView.getPlayersName().split(",")), dealer);
    }

    private void betMoney(Participants participants, Purse purse) {
        for (Player player : participants.getPlayers()) {
            final Bet bet = new Bet(inputView.getBet(player.getName()));
            purse.addMoney(player, bet);
        }
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
