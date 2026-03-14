package domain.participant;

import domain.BlackJackInfo;
import domain.card.Card;
import domain.card.CardNumber;

import java.util.List;
import java.util.ArrayList;

public class HandCards {
    private final List<Card> cards;

    public HandCards() {
        this.cards = new ArrayList<>();
    }

    public HandCards(List<Card> cards) {
        this.cards = cards;
    }

    public void receiveInitialCards(List<Card> firstHandCards) {
        cards.addAll(firstHandCards);
    }

    public void receiveHitCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int baseCardScore = cards.stream()
                .map(Card::getBaseScore)
                .reduce(0, Integer::sum);

        return processAceCard(baseCardScore);
    }

    private int processAceCard(int baseCardScore) {
        int score = baseCardScore;
        boolean isAceExist = cards.stream()
                .anyMatch(handCard -> handCard.getCardNumber() == CardNumber.ACE);
        if (isAceExist && (baseCardScore + BlackJackInfo.SOFT_HAND_PROCESS_SCORE) <= BlackJackInfo.BLACKJACK_SCORE) {
            score += BlackJackInfo.SOFT_HAND_PROCESS_SCORE;
        }
        return score;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public boolean isBust() {
        return calculateScore() > BlackJackInfo.BLACKJACK_SCORE;
    }

    public boolean isBlackJack() {
        return cards.size() == BlackJackInfo.FIRST_CARD_COUNT && calculateScore() == BlackJackInfo.BLACKJACK_SCORE;
    }

    public Card getFirst() {
        return cards.getFirst();
    }
}
