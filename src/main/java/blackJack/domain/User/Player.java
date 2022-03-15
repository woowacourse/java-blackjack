package blackJack.domain.User;

import blackJack.domain.Card.CardFactory;
import blackJack.domain.Card.Cards;

public class Player extends User {
    public Player(String name, Cards cards) {
        super(name, cards);
    }

    @Override
    public boolean hit(CardFactory cardFactory) {
        return cards.add(cardFactory.drawOneCard());
    }

}
