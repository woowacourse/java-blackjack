package domain.game;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardNumber;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    public static final int BUST_BOUND = 21;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void drawCardWhenStart(CardDeck cardDeck) {
        List<Card> drawnCard = cardDeck.drawCardWhenStart();
        cards.addAll(drawnCard);
    }

    public void drawCard(CardDeck cardDeck) {
        Card drawnCard = cardDeck.drawCard();
        cards.add(drawnCard);
    }

    private int decideAceNumber(int totalCardNumber) {
        int totalCardNumberWithAce = totalCardNumber + CardNumber.ACE.getNumber();
        if (isOverBustBound(totalCardNumberWithAce)) {
            return totalCardNumber + CardNumber.ACE_ANOTHER.getNumber();
        }
        return totalCardNumberWithAce;
    }

    private boolean isOverBustBound(int totalWithAce) {
        return totalWithAce > BUST_BOUND;
    }

    public boolean isOverBustBound() {
        return calculateTotalCardNumber() > BUST_BOUND;
    }

    public int calculateTotalCardNumber() {
        int aceCount = countAce();
        return sumWithAce(aceCount);
    }

    private int countAce() {
        return (int) cards.stream()
                .map(Card::getCardNumber)
                .filter(number -> number == CardNumber.ACE)
                .count();
    }

    private int sumWithAce(int aceCount) {
        List<Card> aceRemovedHand = removeAceFromHand();
        int totalCardNumber = calculateCardNumber(aceRemovedHand);
        for (int i = 0; i < aceCount; i++) {
            totalCardNumber = decideAceNumber(totalCardNumber);
        }
        return totalCardNumber;
    }

    private List<Card> removeAceFromHand() {
        return cards.stream()
                .filter(card -> card.getCardNumber() != CardNumber.ACE)
                .toList();
    }

    private int calculateCardNumber(List<Card> cards) {
        return cards.stream()
                .mapToInt(card -> card.getCardNumber().getNumber())
                .sum();
    }

    public int getCardsCount() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }
}
