package controller;

import model.Dealer;
import model.Deck;
import model.User;
import model.Users;
import model.card.Card;
import ui.input.InputView;
import ui.output.OutputView;

import java.util.Arrays;

public class BlackJackController {

    private static final String RECEIVE_CARD_COMMAND = "y";

    private final Deck deck;
    private final Dealer dealer;

    public BlackJackController(final Deck deck) {
        this.deck = deck;
        this.dealer = Dealer.getInstance();
    }

    public void init() {
        Users users = getUsers();
        OutputView.printDivideInitialCards(users);

        receiveCard(users);

        OutputView.printTotalValue(users);
        OutputView.printFinalResult(users);
    }

    private Users getUsers() {
        Users users = new Users(Arrays.asList(InputView.getPlayersName().split(",")));
        users.getUsers().forEach(this::receiveInitialCards);
        return users;
    }

    private void receiveInitialCards(final User user) {
        user.receiveCard(deck.pick());
        user.receiveCard(deck.pick());
    }

    private void receiveCard(final Users users) {
        forPlayer(users);
        forDealer();
    }

    private void forPlayer(final Users users) {
        for (User user : users.getUsers()) {
            wantReceive(user);
        }
    }

    private void wantReceive(final User user) {
        while (canReceiveMoreCard(user)) {
            final Card card = deck.pick();
            user.receiveCard(card);
            OutputView.printPlayerHand(user);
        }
    }

    private boolean canReceiveMoreCard(final User user) {
        return !Dealer.getInstance().equals(user)
                && user.canReceiveCard()
                && RECEIVE_CARD_COMMAND.equals(InputView.getReceiveCardCommand(user.getName()));
    }

    private void forDealer() {
        if (dealer.canReceiveCard()) {
            OutputView.printDealerGetCard();
            dealer.receiveCard(deck.pick());
        }
    }
}
