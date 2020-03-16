package blackjack.controller;

import blackjack.domain.*;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardFactory;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.User;
import blackjack.domain.player.Users;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackJackGame {
    private final Dealer dealer;
    private final CardDeck cardDeck;
    private Users users;

    public BlackJackGame() {
        dealer = new Dealer();
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
        users = new Users(userNames);
    }

    private void distributeCards() {
        dealer.distributeInitialCards(cardDeck);
        for (User user : users.getUsers()) {
            user.distributeInitialCards(cardDeck);
        }
        OutputView.printDistributeConfirmMessage(dealer, users);
        OutputView.printInitialPlayerCards(dealer, users);
    }

    private void play() {
        for (User user : users.getUsers()) {
            eachUserPlay(user);
        }
        dealerPlay();
    }

    private void eachUserPlay(User user) {
        user.changeStatusIfBlackJack();
        while (user.isNoneStatus() && Response.isYes(InputView.askOneMoreCard(user))) {
            user.addCard(cardDeck);
            user.changeStatusIfBust();
            OutputView.printUserCards(user);
        }
    }

    private void dealerPlay() {
        dealer.changeStatusIfBlackJack();
        if (dealer.isUnderCriticalScore()) {
            dealer.addCard(cardDeck);
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
