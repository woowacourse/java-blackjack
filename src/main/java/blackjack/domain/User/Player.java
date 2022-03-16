package blackjack.domain.User;

import blackjack.domain.Card.CardFactory;
import blackjack.domain.Card.Cards;

public class Player extends User {

    private final Betting betting;

    public Player(String name, Betting betting, Cards cards) {
        super(name, cards);
        this.betting = betting;
    }

    @Override
    public void hit(CardFactory cardFactory) {
        cards.add(cardFactory.drawOneCard());
    }

}
