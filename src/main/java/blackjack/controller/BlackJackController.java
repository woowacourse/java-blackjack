package blackjack.controller;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    public void play() {
        Users users = new Users(InputView.inputUsersName());
        Dealer dealer = new Dealer();
        Deck deck = new Deck(new CardGenerator());

        initDistribute(deck, users, dealer);
        OutputView.printInitDistribute(users, dealer);

        // 각각의 유저에 따라 추가 지급
        for (User user : users.getUsers()) {
            playEachUser(user, deck);
        }
        // 승패 여부
    }

    private boolean isContinue(User user) {
        String userData = InputView.inputMoreCard(user);
        if (userData.equals("y")) {
            return true;
        }
        if (userData.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException("[ERROR] y 또는 n만 입력 가능합니다.");
    }

    private void playEachUser(User user, Deck deck) {
        while (!user.checkBust() && isContinue(user)) {
            user.receiveCard(deck.drawCard());
            OutputView.printUserData(user);
        }
    }

    private void initDistribute(Deck deck, Users users, Dealer dealer) {
        for (int i = 0; i < 2; i++) {
            userDistribute(deck, users);
            dealer.receiveCard(deck.drawCard());
        }
    }

    private void userDistribute(Deck deck, Users users) {
        for (User user : users.getUsers()) {
            user.receiveCard(deck.drawCard());
        }
    }
}
