package blackjack.domain.user;

import java.util.List;

import blackjack.domain.card.Card;

public class Dealer extends User {

    private static final int INIT_COUNT = 1;

    public Dealer() {
        super("딜러");
    }

    @Override
    public List<Card> showInitCards() {
        return this.hand.getCards(INIT_COUNT);
    }
}
