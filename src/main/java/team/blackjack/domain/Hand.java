package team.blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Hand {
    private static final int BLACKJACK = 21;
    private static final int BLACKJACK_CARD_COUNT = 2;
    private static final int ACE_UPGRADE_SCORE_THRESHOLD = 10;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public int getScore() {
        if (existAceInCards()) {
            final Map<Boolean, List<Card>> result = cards.stream()
                    .collect(Collectors.partitioningBy(Card::isAce));

            final List<Card> aceCards = result.get(true);
            final List<Card> nonAceCards = result.get(false);
            return calculateBestSumWithAce(nonAceCards, aceCards);
        }
        return calculateBestSumWithoutAce(cards);
    }

    public boolean isBust() {
        return getScore() > BLACKJACK;
    }

    public boolean isBlackjack() {
        if (cards.size() != BLACKJACK_CARD_COUNT) {
            return false;
        }
        return getScore() == BLACKJACK;
    }

    public List<String> getCardNames() {
        return cards.stream()
                .map(Card::getCardName)
                .toList();
    }

    /**
     * private 헬퍼 메소드
     */
    private boolean existAceInCards() {
        for (Card card : cards) {
            if (card.isAce()) {
                return true;
            }
        }
        return false;
    }

    private int calculateBestSumWithAce(List<Card> cardsWithoutAces, List<Card> aceCards) {
        int currentSum = calculateBestSumWithoutAce(cardsWithoutAces);

        for (Card card : aceCards) {
            currentSum += getAceScore(card, currentSum);
        }

        return currentSum;
    }

    private int calculateBestSumWithoutAce(List<Card> cards) {
        return cards.stream()
                .mapToInt(card -> card.getScore().getFirst())
                .sum();
    }

    private int getAceScore(Card card, int currentSum) {
        if (canUseAceAsEleven(currentSum)) {
            return card.getScore().getLast();
        }

        return card.getScore().getFirst();
    }

    private boolean canUseAceAsEleven(int currentSum) {
        return currentSum <= ACE_UPGRADE_SCORE_THRESHOLD;
    }
}
