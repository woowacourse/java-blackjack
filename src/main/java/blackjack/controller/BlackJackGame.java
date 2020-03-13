package blackjack.controller;

import blackjack.domain.*;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardFactory;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.User;
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

    public void run() {
        registerUsers();
        distributeCards();
        play();
        calculateResult();
    }

    public void registerUsers() {
        List<String> userNames = InputView.inputUserNames();
        users = userNames.stream()
                .map(User::new)
                .collect(Collectors.toList());
    }

    private void distributeCards() {
        OutputView.printDistributeConfirmMessage(dealer, users, INITIAL_CARDS_SIZE);
        IntStream.range(START_INDEX, INITIAL_CARDS_SIZE)
                .forEach(i -> dealer.addCard(cardDeck.pop()));

        for (User user : users) {
            IntStream.range(START_INDEX, INITIAL_CARDS_SIZE)
                    .forEach(i -> user.addCard(cardDeck.pop()));
        }
        OutputView.printInitialPlayerCards(dealer, users);
    }

    private void play() {
        for (User user : users) {
            user.changeStatusIfBlackJack();
            eachUserPlay(user);
        }
        dealerPlay();
    }

    private void eachUserPlay(User user) {
        while (user.isNoneStatus() && InputView.askOneMoreCard(user) == Response.YES) {
            user.addCard(cardDeck.pop());
            user.changeStatusIfBust();
            OutputView.printUserCards(user);
        }
    }

    private void dealerPlay() {
        dealer.changeStatusIfBlackJack();
        if (dealer.isUnderCriticalScore()) {
            dealer.addCard(cardDeck.pop());
            dealer.changeStatusIfBust();
            OutputView.printDealerPlayConfirmMessage();
        }
    }

    private void calculateResult() {
        OutputView.printPlayerFinalScore(dealer, users);
        GameResult gameResult = GameResult.calculateGameResult(dealer, users);
        OutputView.printGameResult(gameResult);
    }
}
