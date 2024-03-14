package blackjack.domain.dealer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.participant.Participant;
import blackjack.domain.result.Score;
import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {
    private static final Score NEED_CARD_CRITERION = new Score(17);
    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean needMoreCard() {
        return NEED_CARD_CRITERION.isBiggerThan(calculate());
    }

    public Hands getOpenedCards() {
        List<Card> openedCards = new ArrayList<>();
        openedCards.add(hands.getFirstCard());

        return new Hands(openedCards);
    }
}
