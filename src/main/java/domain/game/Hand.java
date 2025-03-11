package domain.game;

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
        int aceCount = countAce();

        return decideAceValue(totalCardNumber, aceCount);
    }

    private int calculateTotalCardNumber() {
        return cards.stream()
                .mapToInt(card -> card.getCardNumber().getNumber())
                .sum();
    }

    private int countAce() {
        return (int) cards.stream()
                .map(Card::getCardNumber)
                .filter(number -> number == CardNumber.ACE)
                .count();
    }

    private int decideAceValue(int totalWithAce, int aceCount) {
        for (int i = 0; i < aceCount; i++) {
            int totalWithAnotherAce = changeAceNumber(totalWithAce);
            if (totalWithAnotherAce <= BUST_BOUND) {
                totalWithAce = totalWithAnotherAce;
                break;
            }
        }
        return totalWithAce;
    }

    private static int changeAceNumber(int totalWithAce) {
        return (totalWithAce - CardNumber.ACE.getNumber()) + CardNumber.ACE_ANOTHER.getNumber();
    }

    public int getCardsCount() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }
}
