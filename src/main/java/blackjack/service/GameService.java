package blackjack.service;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.User;
import java.util.List;
import java.util.Map;

public class GameService {

    private final Deck deck;

    public GameService(Deck deck) {
        this.deck = deck;
    }

    public void settingCards(List<User> users, Dealer dealer) {
        deck.shuffle();
        for (int i = 0; i < 2; i++) {
            for (User user : users) {
                user.bring(deck.bringTopCard());
            }
            dealer.bring(deck.bringTopCard());
        }
    }

    public void getMoreCard(User user) {
        user.bring(deck.bringTopCard());
    }

    public void getMoreCardForDealer(Dealer dealer) {
        dealer.bring(deck.bringTopCard());
    }

    public boolean isDealerWinning(User user, Dealer dealer) {
        if (user.isBurst()) {
            return true;
        }
        if (dealer.isBurst()) {
            return false;
        }

        return user.calculateCardsValue() < dealer.calculateCardsValue();
    }

    public int applyGameResult(User user, Dealer dealer, Map<String, Boolean> result) {
        boolean isDealerWinning = isDealerWinning(user, dealer);
        result.put(user.getName(), !isDealerWinning);
        if (isDealerWinning) {
            return 1;
        }

        return 0;
    }
}
