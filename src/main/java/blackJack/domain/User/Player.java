package blackJack.domain.User;

import blackJack.domain.Card.CardFactory;
import blackJack.domain.Card.Cards;

public class Player extends User {
    public Player(String name, Cards cards) {
        super(name, cards);
    }

    @Override
    public boolean hit(CardFactory cardFactory) {
        if(!isBust() ){
            cards.add(cardFactory.drawOneCard());
            return true;
        }
        return false;
    }

}
