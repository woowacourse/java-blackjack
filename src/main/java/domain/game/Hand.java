package domain.game;

import static domain.card.CardNumber.VALUE_TO_SOFT_ACE;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardNumber;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    public static final int BUST_BOUND = 21;

    private final List<Card> cards;
    private final CardDeck cardDeck;

    public Hand(CardDeck cardDeck) {
        this.cards = new ArrayList<>();
        this.cardDeck = cardDeck;
    }

    public void drawCard(int drawCount) {
        List<Card> drawnCard = cardDeck.drawCard(drawCount);
        cards.addAll(drawnCard);
    }

    public boolean isOverBustBound() {
        return calculateTotalWithAce() > BUST_BOUND;
    }

    public int calculateTotalWithAce() {
        int totalCardNumber = calculateTotalCardNumber();
        if (hasAce()) {
            return calculateWithAce(totalCardNumber);
        }
        return totalCardNumber;
    }

    private int calculateTotalCardNumber() {
        return cards.stream()
                .mapToInt(card -> card.getCardNumber().getNumber())
                .sum();
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(card -> card.getCardNumber() == CardNumber.ACE);
    }

    private int calculateWithAce(int totalCardNumber) {
        int totalWithAce = totalCardNumber + VALUE_TO_SOFT_ACE;
        if (totalWithAce <= BUST_BOUND) {
            return totalWithAce;
        }
        return totalCardNumber;
    }

    public int getCardsCount() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }
}
