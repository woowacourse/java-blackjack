package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.List;

public class Player extends Gamer {

    public Player(String name) {
        super(name, new Cards(new ArrayList<>()));
    }

    @Override
    public List<Card> getViewCard() {
        return getCards().getCards();
    }
}
