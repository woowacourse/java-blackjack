package domain.user;

import domain.TrumpCard;
import java.util.List;

public class Dealer extends User {
    public Dealer(String name) {
        super(name);
    }

    @Override
    public List<TrumpCard> openCard() {
        return this.cardDeck.getFirstCard();
    }
}
