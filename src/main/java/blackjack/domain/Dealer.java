package blackjack.domain;

import java.util.Collections;
import java.util.Set;

public class Dealer extends Participant {
    private final static String DEALER_NAME = "딜러";
    private final static int DEALER_HIT_THRESHOLD = 16;

    private final CardProvider cardProvider;

    public Dealer(CardProvider cardProvider) {
        super();
        this.cardProvider = cardProvider;
    }

    public void initCardDeck() {
        cardStorage.add(cardProvider.drawCard());
        cardStorage.add(cardProvider.drawCard());
    }

    public CardDeck createCardDeck() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.add(cardProvider.drawCard());
        cardDeck.add(cardProvider.drawCard());
        return cardDeck;
    }

    public Card drawCard() {
        return cardProvider.drawCard();
    }

    @Override
    public boolean canHit() {
        int score = calculateTotalCardScore();
        return score <= DEALER_HIT_THRESHOLD;
    }

    @Override
    public int calculateTotalCardScore() {
        Set<Integer> possibleScore = cardStorage.calculatePossibleSums();
        return Collections.max(possibleScore);
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }
}
