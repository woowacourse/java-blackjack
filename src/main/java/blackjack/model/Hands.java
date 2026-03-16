package blackjack.model;

import blackjack.model.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Hands {

    private static final int INTIAL_CARD_COUNT = 2;

    private final List<Card> cards;

    private Hands(List<Card> cards) {
        if (cards == null) {
            throw new IllegalArgumentException("cards가 null입니다.");
        }

        this.cards = cards;
    }

    public static Hands empty() {
        return new Hands(new ArrayList<>());
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateTotalScore() {
        return calculateScoreOf(this.cards);
    }

    public boolean isTotalScoreOver(final int score) {
        return calculateTotalScore() > score;
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
        if (cards.size() < INTIAL_CARD_COUNT) {
            throw new IllegalStateException("초기 " + INTIAL_CARD_COUNT + " 장의 카드 분배가 완료 되지 않았습니다.");
        }

        return calculateScoreOf(cards.subList(0, INTIAL_CARD_COUNT));
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
