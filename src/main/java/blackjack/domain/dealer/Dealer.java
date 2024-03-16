package blackjack.domain.dealer;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;
import blackjack.domain.result.Score;
import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {
    public static final int CARD_REQUEST_CRITERION = 16;
    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean needMoreCard() {
        return new Score(CARD_REQUEST_CRITERION).isMoreOrEqualThan(calculate());
    }

    public List<Card> getOpenedCards() {
        List<Card> openedCards = new ArrayList<>();
        openedCards.add(hands.getFirstCard());

        return openedCards;
    }
}
