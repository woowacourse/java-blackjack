package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.List;

public class Player extends User {
    public Player(String name) {
        super(name);
    }

    public Player(String name, int money) {
        super(name, money);
    }

    @Override
    public List<Card> getInitialCards() {
        return super.getCards();
    }
}
