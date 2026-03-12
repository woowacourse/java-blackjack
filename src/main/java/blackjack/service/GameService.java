package blackjack.service;

import static blackjack.util.constant.Constants.INITIAL_CARD_COUNT;

import blackjack.domain.participant.Dealer;
import blackjack.domain.deck.Deck;
import blackjack.domain.GameResult;
import blackjack.domain.participant.User;
import blackjack.domain.participant.Users;

public class GameService {

    private final Deck deck;

    public GameService(Deck deck) {
        this.deck = deck;
    }

    public void settingCards(Users users, Dealer dealer) {
        deck.shuffle();
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            distributeOneCard(users, dealer);
        }
    }

    private void distributeOneCard(Users users, Dealer dealer) {
        users.forEach(user -> user.bring(deck.bringTopCard()));
        dealer.bring(deck.bringTopCard());
    }

    public void getMoreCard(User user) {
        user.bring(deck.bringTopCard());
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

    public void applyGameResult(User user, Dealer dealer, GameResult gameResult) {
        boolean isUserWin = !isDealerWinning(user, dealer);
        gameResult.add(user.getName(), isUserWin);
    }
}
