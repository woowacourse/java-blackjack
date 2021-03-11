package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Participant {
    public static final String DEALER = "딜러";
    public static final int STAY_THRESHOLD = 17;

    public boolean isStay() {
        return cardResult() >= STAY_THRESHOLD;
    }

    @Override
    public List<Card> getOpenCard() {
        return cards.subList(0,1);
    }

    @Override
    public String getName() {
        return DEALER;
    }
}
