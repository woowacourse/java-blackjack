package domain.user;

import domain.CardDeck;
import domain.TrumpCard;
import java.util.List;

public class Player extends User {
    public Player(String name) {
        super(name);
    }

    @Override
    public boolean isImpossibleDraw() {
        return cardDeck.isImpossibleDraw(CardDeck.MAX_SCORE);
    }

    @Override
    public List<TrumpCard> openCard() {
        return this.cardDeck.getAllCard();
    }


}
