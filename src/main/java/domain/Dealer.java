package domain;

import java.util.List;

public class Dealer extends Player {

    public Dealer() {
        super("딜러");
    }

    @Override
    public List<Card> openInitialCards() {
        return getCards().getCards(1);
    }
}
