package domain;

import java.util.List;

public class Cards {
    private List<Card> cardsDeck;

    public Cards() {
        this.cardsDeck = Card.getCards();
    }

}
