package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.List;

public class Dealer extends Player {

    public static final String DEALER_NAME = "딜러";
    private static final int DEALER_RECEIVE_STANDARD = 16;
    private static final int DEALER_OPEN_CARD_SIZE = 1;

    public Dealer() {
        super(DEALER_NAME, new Cards());
    }

    @Override
    public List<Card> openCards() {
        return new ArrayList<>(cards.getCards().subList(0, DEALER_OPEN_CARD_SIZE));
    }

    @Override
    public boolean isReceivable() {
        return calculateResult() <= DEALER_RECEIVE_STANDARD;
    }

}
