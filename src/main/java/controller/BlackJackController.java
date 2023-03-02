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
        Users users = new Users(Arrays.asList(InputView.getPlayersName().split(",")));

        users.getUsers().forEach(this::receiveInitialCards);
        OutputView.printDivideInitialCards(users);

        for (User user : users.getUsers()) {
            while (canReceiveMoreCard(user)) {
                final Card card = deck.pick();
                user.receiveCard(card);
                OutputView.printPlayerHand(user);
            }
        }

        if (dealer.canReceiveCard()) {
            OutputView.printDealerGetCard();
            dealer.receiveCard(deck.pick());
        }

        OutputView.printTotalValue(users);
    }
    private void receiveInitialCards(final User user) {
        user.receiveCard(deck.pick());
        user.receiveCard(deck.pick());
    }

    private static boolean canReceiveMoreCard(final User user) {
        return !Dealer.getInstance().equals(user)
                && user.canReceiveCard()
                && RECEIVE_CARD_COMMAND.equals(InputView.getReceiveCardCommand(user.getName()));
    }
}
