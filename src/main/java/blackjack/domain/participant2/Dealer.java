package blackjack.domain.participant2;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.hand.CardHand;
import java.util.List;
import java.util.stream.Collectors;

public final class Dealer extends BlackjackParticipant {

    private static final String DEALER_NAME = "딜러";
    private static final int OPEN_CARDS_COUNT = 1;
    private static final int DEALER_MAX_HIT_SCORE = 16;

    public Dealer(final CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }

    @Override
    public List<Card> openInitialCards() {
        return cardHand.getCardBundle()
                .getCards()
                .stream()
                .limit(OPEN_CARDS_COUNT)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    protected boolean shouldStay() {
        CardBundle cardBundle = cardHand.getCardBundle();
        int scoreInt = cardBundle.getScoreInt();
        return scoreInt > DEALER_MAX_HIT_SCORE;
    }

    @Override
    public String toString() {
        return "Dealer{" + "cardHand=" + cardHand + '}';
    }
}
