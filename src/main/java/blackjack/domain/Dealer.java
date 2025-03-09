package blackjack.domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Dealer extends Participant {
    private final static String DEALER_NAME = "딜러";
    private final static int DEALER_HIT_THRESHOLD = 16;

    private final CardDump cardDump;

    public Dealer(CardDeck cardDeck, CardDump cardDump) {
        super(cardDeck);
        this.cardDump = cardDump;
    }

    public Card giveCardToPlayer() {
        return cardDump.drawCard();
    }

    public List<Card> giveCardsToPlayer() {
        return cardDump.drawCards();
    }

    public void createInitialCardDeck() {
        cardDeck.addAll(cardDump.drawCards());
    }

    public void addCard() {
        cardDeck.add(cardDump.drawCard());
    }

    @Override
    public boolean canHit() {
        int score = calculateTotalCardScore();
        return score <= DEALER_HIT_THRESHOLD;
    }

    @Override
    public int calculateTotalCardScore() {
        Set<Integer> possibleScore = cardDeck.calculatePossibleSum();
        return Collections.max(possibleScore);
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }
}
