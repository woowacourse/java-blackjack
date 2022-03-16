package blackjack.domain.User;

import blackjack.domain.Card.CardFactory;
import blackjack.domain.Card.Cards;

public class Player extends User {
    public Player(String name, Cards cards) {
        super(name, cards);
    }

    @Override
    public void hit(CardFactory cardFactory) {
        cards.add(cardFactory.drawOneCard());
    }

}
