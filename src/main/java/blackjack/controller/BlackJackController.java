package blackjack.controller;

import blackjack.domain.CardGenerator;
import blackjack.domain.Deck;
import blackjack.domain.Dealer;
import blackjack.domain.User;
import blackjack.domain.Users;
import blackjack.domain.DealerResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    public void play() {
        Users users = new Users(InputView.inputUsersName());
        Dealer dealer = new Dealer();
        Deck deck = new Deck(new CardGenerator());
        initDistribute(deck, users, dealer);

        playGame(users, dealer, deck);
        OutputView.printFinalCard(users, dealer);
        calculateResult(users, dealer);
    }

    private void initDistribute(Deck deck, Users users, Dealer dealer) {
        for (int i = 0; i < 2; i++) {
            userDistribute(deck, users);
            dealer.receiveCard(deck.drawCard());
        }
        OutputView.printInitDistribute(users, dealer);
    }

    private void userDistribute(Deck deck, Users users) {
        for (User user : users.getUsers()) {
            user.receiveCard(deck.drawCard());
        }
    }

    private void playGame(Users users, Dealer dealer, Deck deck) {
        for (User user : users.getUsers()) {
            playEachUser(user, deck);
        }

        playDealer(dealer, deck);
    }

    private void playDealer(Dealer dealer, Deck deck) {
        while (dealer.checkUnderSumStandard()) {
            OutputView.printDealerDraw();
            dealer.receiveCard(deck.drawCard());
        }
    }

    private void playEachUser(User user, Deck deck) {
        while (!user.checkBust() && InputView.inputMoreCard(user)) {
            user.receiveCard(deck.drawCard());
            OutputView.printUserData(user);
        }
    }

    private void calculateResult(Users users, Dealer dealer) {
        DealerResult result = new DealerResult(users, dealer);
        int dealerSum = dealer.getCardSum();

        OutputView.printFinalScore(result, users, dealerSum);
    }
}
