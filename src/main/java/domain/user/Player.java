package domain.user;

import domain.TrumpCard;
import java.util.List;

public class Player extends User {
    public Player(String name) {
        super(name);
    }

    @Override
    public List<TrumpCard> openCard() {
        return this.cardDeck.getAllCard();
    }
}
