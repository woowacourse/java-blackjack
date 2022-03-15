package blackJack.domain.User;

import blackJack.domain.Card.CardFactory;
import blackJack.domain.Card.Cards;

public class Player extends User {
    public Player(String name, Cards cards) {
        super(name, cards);
    }

    @Override
    public boolean hit(CardFactory cardFactory) {
        cards.add(cardFactory.drawOneCard());
        return true;
    }

}
