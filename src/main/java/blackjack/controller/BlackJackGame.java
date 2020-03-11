package blackjack.controller;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BlackJackGame {
    private static final int INITIAL_CARDS_SIZE = 2;
    private static final int START_INDEX = 0;

    private final Dealer dealer;
    private final CardDeck cardDeck;
    private List<User> users;

    public BlackJackGame() {
        dealer = Dealer.getDealer();
        cardDeck = new CardDeck(CardFactory.createCardDeck());
    }

    public void play() {
        enrollUsers();
        distributeCards();
        OutputView.printInitialPlayerCards(dealer, users);
    }

    public void enrollUsers() {
        users = InputView.inputUserNames().stream()
                .map(User::new)
                .collect(Collectors.toList());
    }

    private void distributeCards() {
        OutputView.printDistributeConfirmMessage(dealer, users);
        IntStream.range(START_INDEX, INITIAL_CARDS_SIZE)
                .forEach(i -> dealer.addCard(cardDeck.getOneCard()));

        for (User user : users) {
            IntStream.range(START_INDEX, INITIAL_CARDS_SIZE)
                    .forEach(i -> user.addCard(cardDeck.getOneCard()));
        }
    }

}
