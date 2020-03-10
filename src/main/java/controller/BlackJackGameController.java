package controller;

import java.util.ArrayList;
import java.util.List;

import domain.card.CardDeck;
import domain.player.Dealer;
import domain.player.User;
import utils.InputUtils;
import view.InputView;
import view.OutputView;

public class BlackJackGameController {

    public static void run() {
        String name = InputView.inputUserNames();
        List<User> users = userNamesSetting(name);
        OutputView.initialSetting(users);

        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck();
        dealer.firstDraw(cardDeck);
        for (User user : users) {
            user.firstDraw(cardDeck);
        }

        OutputView.printOneCard(dealer);
        for (User user : users) {
            OutputView.printCardStatus(user);
        }

        for (User user : users) {
            while (InputUtils.isHit(InputView.inputIsHit(user))) {
                user.receive(cardDeck);
                OutputView.printCardStatus(user);
            }
        }
        dealerHit(dealer, cardDeck);

        OutputView.printFinalScore(dealer);
        for (User user : users) {
            OutputView.printFinalScore(user);
        }
    }

    public static void dealerHit(Dealer dealer, CardDeck cardDeck) {
        while (dealer.calculateScore() <= 16) {
            OutputView.printDealerAdditionalCard();
            dealer.receive(cardDeck);
        }
    }

    public static List<User> userNamesSetting(String names) {
        List<User> users = new ArrayList<>();
        String[] userNames = names.split(",");
        for (String name : userNames) {
            users.add(new User(name));
        }
        return users;
    }

}
