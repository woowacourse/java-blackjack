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
        int baseCardScore = handCards.stream()
                .map(Card::getBaseScore)
                .reduce(0, Integer::sum);

        return processAceCard(baseCardScore);
    }

    private int processAceCard(int baseCardScore) {
        int score = baseCardScore;
        boolean isAceExist = handCards.stream()
                .anyMatch(handCard -> handCard.getCardNumber() == CardNumber.ACE);
        if (isAceExist && (baseCardScore + 10) <= 21) {
            score += 10;
        }
        return score;
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
