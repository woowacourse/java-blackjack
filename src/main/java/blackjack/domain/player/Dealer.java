package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.List;

public class Dealer extends Player {

    public static final String DEALER_NAME = "딜러";

    private static final int DEALER_OPEN_CARD_NUMBER = 1;
    private static final int DEALER_RECEIVE_STANDARD = 16;

    private final String name;

    public Dealer() {
        super(new Cards());
        name = DEALER_NAME;
    }

    @Override
    public List<Card> openCards() {
        return new ArrayList<>(cards.getCards().subList(0, DEALER_OPEN_CARD_NUMBER));
    }

    @Override
    public boolean isSatisfyReceiveCondition() {
        return calculateResult() <= DEALER_RECEIVE_STANDARD;
    }

    @Override
    public String getName() {
        return name;
    }

}
