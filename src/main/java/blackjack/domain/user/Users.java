package blackjack.domain.user;

import blackjack.domain.card.CardDeck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Users {
    private static final int INITIAL_DRAW_CARD_NUMBER = 2;

    private final List<User> users = new ArrayList<>();

    public Users(Dealer dealer, Players players) {
        this.users.add(dealer);
        this.users.addAll(new ArrayList<>(players.players()));
    }

    public void dealTwoCards(CardDeck cardDeck) {
        for (int i = 0; i < INITIAL_DRAW_CARD_NUMBER; i++) {
            this.users.forEach(user -> user.addCard(cardDeck.drawCard()));
        }
    }

    public List<User> users() {
        return Collections.unmodifiableList(this.users);
    }
}
