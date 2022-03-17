package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import java.util.List;
import java.util.stream.Collectors;

public class Dealer extends Participant {

    public static final String UNIQUE_NAME = "딜러";
    private static final int INITIAL_DEALER_OPEN_CARDS_COUNT = 1;

    private Dealer(final CardBundle cardBundle) {
        super(cardBundle, CardBundle::isDealerFinished);
    }

    public static Dealer of(final CardBundle cardBundle) {
        return new Dealer(cardBundle);
    }

    @Override
    public String getName() {
        return UNIQUE_NAME;
    }

    @Override
    public List<Card> getInitialOpenCards() {
        return cardHand.getCardBundle()
                .getCards()
                .stream()
                .limit(INITIAL_DEALER_OPEN_CARDS_COUNT)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public String toString() {
        return "Dealer{" + "cardHand=" + cardHand + '}';
    }
}
