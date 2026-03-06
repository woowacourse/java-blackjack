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
        int results = 0;
        for (Card holdCard : handCards) {
            results += holdCard.getScore();
        }

        boolean isAceExist = handCards.stream()
                .anyMatch(holdCard -> holdCard.getCardNumber() == CardNumber.ACE);
        if (isAceExist && (results + 10) <= 21) {
            return results + 10;
        }
        return results;
    }

    public List<Card> getHandCards(){
        return List.copyOf(handCards);
    }

    public boolean isBust() {
        return calculateTotalScore() > 21;
    }
}
