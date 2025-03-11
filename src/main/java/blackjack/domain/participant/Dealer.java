package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import java.util.List;

public class Dealer extends Participant {
    private final static String DEALER_NAME = "딜러";
    private final static int DEALER_HIT_THRESHOLD = 16;

    public Dealer(CardDeck cardDeck) {
        super(cardDeck);
    }

    @Override
    public boolean canHit() {
        int hitScore = calculateScore();
        return hitScore <= DEALER_HIT_THRESHOLD;
    }

    @Override
    public List<Card> showStartCards() {
        Card firstCard = cardDeck.getCards().getFirst();
        return List.of(firstCard);
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }
}
