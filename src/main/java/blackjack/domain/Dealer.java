package blackjack.domain;

import java.util.Collections;
import java.util.Set;

public class Dealer extends Participant {
    private final static String DEALER_NAME = "딜러";
    private final static int DEALER_HIT_THRESHOLD = 16;

    private final CardDump cardDump;

    public Dealer(CardDump cardDump) {
        super(new CardDeck());
        this.cardDump = cardDump;
    }

    public void initCardDeck() {
        cardDeck.add(cardDump.drawCard());
        cardDeck.add(cardDump.drawCard());
    }

    public CardDeck createCardDeck() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.add(cardDump.drawCard());
        cardDeck.add(cardDump.drawCard());
        return cardDeck;
    }

    public Card drawCard() {
        return cardDump.drawCard();
    }

    @Override
    public boolean canHit() {
        int score = calculateTotalCardScore();
        return score <= DEALER_HIT_THRESHOLD;
    }

    @Override
    public int calculateTotalCardScore() {
        Set<Integer> possibleScore = cardDeck.calculatePossibleSums();
        return Collections.max(possibleScore);
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }
}
