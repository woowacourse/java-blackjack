package controller;

import model.Deck;
import model.Result;
import model.User;
import model.Users;
import model.card.Card;
import ui.input.InputView;
import ui.output.OutputView;

import java.util.Arrays;

public class BlackJackController {

    private static final String RECEIVE_CARD_COMMAND = "y";

    private final Deck deck;

    public BlackJackController(final Deck deck) {
        this.deck = deck;
    }

    public void init() {
        Users users = new Users(Arrays.asList(InputView.getPlayersName().split(",")));
        Result result = new Result(users);

        divideInitialCard(users, result);
        OutputView.printDivideInitialCards(result);

        for (User user : users.getUsers()) {
            while (canReceiveMoreCard(result, user)) {
                receiveMoreCard(result, user);
                OutputView.printPlayerCardsInfo(user, result);
            }
        }
        dealerReceiveCard(result);
        for (User user : users.getUsers()) {
            final int totalCardValue = result.calculateTotalCardValue(user);
            OutputView.printTotalValue(result.getUserScoreBoards(user), user, totalCardValue);
        }
    }

    private static boolean canReceiveMoreCard(final Result result, final User user) {
        return !"딜러".equals(user.getName())
                && result.canPlayerReceiveCard(user)
                && RECEIVE_CARD_COMMAND.equals(InputView.getReceiveCardCommand(user.getName()));
    }

    private void divideInitialCard(final Users users, final Result result) {
        for (User user : users.getUsers()) {
            divideInitialCard(result, user);
        }
    }

    private void divideInitialCard(final Result result, final User user) {
        receiveMoreCard(result, user);
        receiveMoreCard(result, user);
    }

    private void receiveMoreCard(final Result result, final User user) {
        final Card card = deck.pick();
        result.addCard(user, card);
    }

    private void dealerReceiveCard(final Result result) {
        if (result.canDealerReceiveCard()) {
            OutputView.printDealerGetCard();
        }
    }
}
