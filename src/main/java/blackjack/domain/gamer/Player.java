package blackjack.domain.gamer;

import blackjack.domain.card.Card;

import java.util.List;

public class Player extends Gamer {

    public Player(String name) {
        super(name);
    }

    @Override
    public List<Card> showOpenHands() {
        return hands.getCardsWithSize(2);
    }
}
