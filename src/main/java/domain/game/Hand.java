package domain.game;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardNumber;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    public static final int BURST_BOUND = 21;

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
        if (isOverBurstBound(totalCardNumberWithAce)) {
            return totalCardNumber + CardNumber.ACE_ANOTHER.getNumber();
        }
        return totalCardNumberWithAce;
    }

    public boolean isOverBurstBound(int totalCardNumber) {
        return totalCardNumber > BURST_BOUND;
    }

    public int calculateTotalCardNumber() {
        int aceCount = countAce();
        if (aceCount > 0) {
            return sumWithAcesCount(aceCount);
        }
        return calculateCardNumber(cards);
    }

    private int countAce() {
        return (int) cards.stream()
                .map(Card::getCardNumber)
                .filter(number -> number == CardNumber.ACE)
                .count();
    }

    private int sumWithAcesCount(int aceCount) {
        List<Card> removedAceHand = removeAce();
        int totalExceptAce = calculateCardNumber(removedAceHand);
        for (int i = 0; i < aceCount; i++) {
            totalExceptAce = decideAceNumber(totalExceptAce);
        }
        return totalExceptAce;
    }

    private List<Card> removeAce() {
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
