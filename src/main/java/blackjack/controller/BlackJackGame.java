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
    private final GameResult gameResult;
    private List<User> users;

    public BlackJackGame() {
        dealer = Dealer.getDealer();
        cardDeck = new CardDeck(CardFactory.createCardDeck());
        gameResult = new GameResult();
    }

    public void run() {
        enrollUsers();
        distributeCards();
        play();
        calculateResult();
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
        OutputView.printInitialPlayerCards(dealer, users);
    }

    private void play() {
        for (User user : users) {
            // 블랙잭인지 확인하고 상태 변경
            eachUserPlay(user);
        }
        dealerPlay();
    }

    private void eachUserPlay(User user) {
        while (InputView.askOneMoreCard(user) == Response.YES) {
            user.addCard(cardDeck.getOneCard());
            // 버스트인지 확인하고 상태 변경
            OutputView.printUserCards(user);
        }
    }

    private void dealerPlay() {
        if (dealer.isUnderCriticalScore()) {
            dealer.addCard(cardDeck.getOneCard());
            OutputView.printDealerPlayConfirmMessage();
        }
    }

    private void calculateResult() {
        OutputView.printPlayerFinalScore(dealer, users);
        gameResult.calculateGameResult(dealer, users);
        OutputView.printGameResult(gameResult);
    }
}
