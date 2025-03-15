package domain.game;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardNumber;
import java.util.ArrayList;
import java.util.List;

public abstract class Participant {

    protected static final int BURST_BOUND = 21;

    protected final List<Card> cards;

    public Participant() {
        this.cards = new ArrayList<>();
    }

    public final void drawCardWhenStart(CardDeck cardDeck) {
        cards.addAll(cardDeck.drawCardWhenStart());
    }

    public final void drawCard(CardDeck cardDeck) {
        Card drawnCard = cardDeck.drawCard();
        cards.add(drawnCard);
    }

    public final boolean isOverBurstBound() {
        int totalCardNumber = calculateTotalCardNumber();
        return isOverBurstBound(totalCardNumber);
    }

    private boolean isOverBurstBound(int totalCardNumber) {
        return totalCardNumber > BURST_BOUND;
    }

    public final int calculateTotalCardNumber() {
        int aceCount = countAce();
        if (aceCount > 0) {
            return sumWithAcesCount(aceCount);
        }
        return calculateCardNumber(cards);
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(card -> card.cardNumber() == CardNumber.ACE)
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
                .filter(card -> card.cardNumber() != CardNumber.ACE)
                .toList();
    }

    private int calculateCardNumber(List<Card> cards) {
        return cards.stream()
                .mapToInt(card -> card.cardNumber().getNumber())
                .sum();
    }

    private int decideAceNumber(int totalCardNumber) {
        int totalCardNumberWithAce = totalCardNumber + CardNumber.ACE.getNumber();
        if (isOverBurstBound(totalCardNumberWithAce)) {
            return totalCardNumber + CardNumber.ACE_ANOTHER.getNumber();
        }
        return totalCardNumberWithAce;
    }

    public final int getCardsCount() {
        return cards.size();
    }

    public final List<Card> getCards() {
        return cards;
    }
}
