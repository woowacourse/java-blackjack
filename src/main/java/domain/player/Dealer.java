package domain.player;

import domain.card.Card;
import java.util.List;

public class Dealer extends Player {
    private static final int MORE_CARD_CRITERIA = 16;
    private static final int FIRST_CARD_OPEN_INDEX = 0;
    private static final String DEFAULT_DEALER_NAME = "딜러";

    public Dealer() {
        super(DEFAULT_DEALER_NAME);
    }

    public Dealer(String name) {
        super(name);
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public boolean canGetMoreCard() {
        return getScore() <= MORE_CARD_CRITERIA;
    }

    @Override
    public List<Card> getOpenCards() {
        return List.of(cards.getCards().get(FIRST_CARD_OPEN_INDEX));
    }
}
