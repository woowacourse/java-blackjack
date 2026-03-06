package domain;

import domain.card.Card;
import domain.card.CardNumber;

import java.util.ArrayList;
import java.util.List;

public abstract class Participant {
    private List<Card> handCards;

    public Participant() {
        this.handCards = new ArrayList<>();
    }

    public void receiveFirstHandCards(List<Card> firstHandCards) {
        handCards = firstHandCards;
    }

    public void addCard(Card card) {
        handCards.add(card);
    }

    public int calculateScore() {
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
        return calculateScore() > 21;
    }

    public void receiveMoreCard(Card card) {
        handCards.add(card);
    }
}
