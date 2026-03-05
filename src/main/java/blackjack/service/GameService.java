package blackjack.service;

import blackjack.domain.Dealer;
import blackjack.domain.User;
import java.util.List;

public class GameService {

    public void settingCards(List<User> users, Dealer dealer) {
        dealer.shuffleCards();
        for (int i = 0; i < 2; i++) {
            for (User user : users) {
                user.bring(dealer.bringCard());
            }
            dealer.bring(dealer.bringCard());
        }
    }

}
