package controller;

import java.util.ArrayList;
import java.util.List;

import domain.card.CardDeck;
import domain.player.Dealer;
import domain.player.User;
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
