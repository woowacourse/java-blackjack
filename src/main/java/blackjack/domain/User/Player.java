package blackjack.domain.User;

import blackjack.domain.Card.CardFactory;
import blackjack.domain.Card.Cards;

public class Player extends User {
    public Player(String name, Cards cards) {
        super(name, cards);
    }

    @Override
    public boolean hit(CardFactory cardFactory) {
        return cards.add(cardFactory.drawOneCard());
    }

}
