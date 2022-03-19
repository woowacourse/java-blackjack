package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.hand.CardHand;
import java.util.List;
import java.util.stream.Collectors;

public final class Dealer extends BlackjackParticipant {

    public static final String NAME = "딜러";
    private static final int OPEN_CARDS_COUNT = 1;
    private static final int MAX_HIT_SCORE = 16;

    public Dealer(final CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public String getName() {
        return NAME;
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
        return cardBundle.hasScoreOver(MAX_HIT_SCORE);
    }

    @Override
    public String toString() {
        return "Dealer{" + "cardHand=" + cardHand + '}';
    }
}
