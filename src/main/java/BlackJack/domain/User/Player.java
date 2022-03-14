package BlackJack.domain.User;

import BlackJack.domain.Card.CardFactory;
import BlackJack.domain.Card.Cards;

public class Player extends User {
    public Player(String name, Cards cards) {
        super(name, cards);
    }

    @Override
    public void addCard(CardFactory cardFactory) {
        cards.add(cardFactory.drawOneCard());
    }

}
