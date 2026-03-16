package blackjack.model;

import blackjack.model.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Hands {

    private static final int INITIAL_CARD_COUNT = 2;

    private final List<Card> cards;

    private Hands(List<Card> cards) {
        if (cards == null) {
            throw new IllegalArgumentException("cards가 null입니다.");
        }

        this.cards = cards;
    }

    public static Hands createEmptyHand() {
        return new Hands(new ArrayList<>());
    }

    public void addCard(Card card) {
         if (card == null) {
             throw new IllegalArgumentException("card가 null입니다.");
         }

        cards.add(card);
    }

    public boolean isTotalScoreOver(final int score) {
        return calculateTotalScore() > score;
    }

    public int calculateTotalScore() {
        return calculateScoreOf(this.cards);
    }

    public List<Card> getOpenedCards() {
        return List.copyOf(this.cards.stream()
                .filter(Card::isOpened)
                .toList());
    }

    public List<Card> getAllCard() {
        return List.copyOf(cards);
    }

    public int calculateInitialCardScore() {
        if (cards.size() < INITIAL_CARD_COUNT) {
            throw new IllegalStateException("초기 " + INITIAL_CARD_COUNT + " 장의 카드 분배가 완료 되지 않았습니다.");
        }

        return calculateScoreOf(cards.subList(0, INITIAL_CARD_COUNT));
    }

    private int calculateScoreOf(List<Card> cards) {
        int baseScore = cards.stream()
                .mapToInt(Card::getDefaultScore)
                .sum();

        boolean hasAce = cards.stream()
                .anyMatch(Card::isAce);

        int adjustmentThreshold = 11;
        int aceAdjustment = 10;

        if (hasAce && baseScore <= adjustmentThreshold) {
            baseScore += aceAdjustment;
        }

        return baseScore;
    }
}
