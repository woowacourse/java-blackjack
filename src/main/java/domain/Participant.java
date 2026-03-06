package domain;

import domain.card.Card;
import domain.card.CardNumber;

import java.util.List;

public abstract class Participant {
    private final List<Card> handCards;

    public Participant(List<Card> handCards) {
        this.handCards = handCards;
    }

    public void addCard(Card card) {
        handCards.add(card);
    }

    public int calculateTotalScore() {
        int cardScore = handCards.stream()
                .map(Card::getScore)
                .reduce(0, Integer::sum);

        boolean isAceExist = handCards.stream()
                .anyMatch(holdCard -> holdCard.getCardNumber() == CardNumber.ACE);

        if (isAceExist && (cardScore + 10) <= 21) {
            return cardScore + 10;
        }
        return cardScore;
    }

    public List<Card> getHandCards() {
        return List.copyOf(handCards);
    }

    public boolean isBust() {
        return calculateTotalScore() > 21;
    }
}
