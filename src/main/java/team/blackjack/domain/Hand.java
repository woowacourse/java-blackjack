package team.blackjack.domain;

import static team.blackjack.domain.rule.DefaultBlackjackRule.BLACKJACK;
import static team.blackjack.domain.rule.DefaultBlackjackRule.BLACKJACK_CARD_COUNT;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import team.blackjack.domain.rule.DefaultBlackjackRule;

public class Hand {

    private final Set<Card> cards = new LinkedHashSet<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards.stream().toList();
    }

    public List<String> getCardNames() {
        return cards.stream()
                .map(Card::getCardName)
                .toList();
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


    public int getScore() {
        if (containsAceCard(cards)) {
            final Map<Boolean, Set<Card>> result = cards.stream()
                    .collect(Collectors.partitioningBy(Card::isAce, Collectors.toSet()));

            final Set<Card> aceCards = result.get(true);
            final Set<Card> nonAceCards = result.get(false);
            return calculateBestSumWithAce(nonAceCards, aceCards);
        }
        return calculateBestSumWithoutAce(cards);
    }

    private boolean containsAceCard(Set<Card> cards) {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private int calculateBestSumWithAce(Set<Card> cardsWithoutAces, Set<Card> aceCards) {
        int currentSum = calculateBestSumWithoutAce(cardsWithoutAces);

        for (Card card : aceCards) {
            currentSum += aceScore(card, currentSum);
        }

        return currentSum;
    }

    private int calculateBestSumWithoutAce(Set<Card> cards) {
        return cards.stream()
                .flatMap(card -> card.getScore().stream())
                .mapToInt(Integer::intValue)
                .sum();
    }

    private int aceScore(Card card, int currentSum) {
        if (currentSum <= 10) {
            return card.getScore().stream()
                    .max(Integer::compareTo)
                    .orElseThrow(() -> new IllegalStateException("ACE 카드의 점수가 존재하지 않습니다."));
        }

        return card.getScore().stream()
                .min(Integer::compareTo)
                .orElseThrow(() -> new IllegalStateException("ACE 카드의 점수가 존재하지 않습니다."));
    }
}
