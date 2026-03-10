package domain.participant;

import domain.Score;
import domain.ScoreStatus;
import domain.card.Card;
import domain.card.CardNumber;

import java.util.List;
import java.util.ArrayList;

public class HandCards {
    List<Card> cards;

    public HandCards() {
        this.cards = new ArrayList<>();
    }

    public HandCards(List<Card> cards) {
        this.cards = cards;
    }

    public void receiveInitialCards(List<Card> firstHandCards) {
        cards.addAll(firstHandCards);
        cards = firstHandCards;
    }

    public void receiveHitCard(Card card) {
        cards.add(card);
    }

    public Score getScore() {
        int score = calculateScore();
        if (isBlackJack()) {
            return new Score(score, ScoreStatus.BLACKJACK);
        }
        if (isBust()) {
            return new Score(score, ScoreStatus.BUST);
        }
        return new Score(score, ScoreStatus.STAND);
    }

    private int calculateScore() {
        int baseCardScore = cards.stream()
                .map(Card::getBaseScore)
                .reduce(0, Integer::sum);

        return processAceCard(baseCardScore);
    }

    private int processAceCard(int baseCardScore) {
        int score = baseCardScore;
        boolean isAceExist = cards.stream()
                .anyMatch(handCard -> handCard.getCardNumber() == CardNumber.ACE);
        if (isAceExist && (baseCardScore + 10) <= 21) {
            score += 10;
        }
        return score;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public boolean isBust() {
        return calculateScore() > 21;
    }

    public boolean isBlackJack() {
        return cards.size() == 2 && calculateScore() == 21;
    }
}
