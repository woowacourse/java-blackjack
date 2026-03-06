package blackjack.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CardCalculator {

    public boolean calculateBlackjack(List<Card> cards) {
        List<Card> initCards = cards.subList(0, 2);
        return totalScore(initCards) == 21;
    }

    public int totalScore(List<Card> cards) {
        Map<Boolean, List<Card>> partitioned = cards.stream()
                .collect(Collectors.partitioningBy(card ->
                        card.equals(Card.A_CLOVER) ||
                                card.equals(Card.A_DIA) ||
                                card.equals(Card.A_SPADE) ||
                                card.equals(Card.A_HEART)
                ));

        List<Card> aceCards = partitioned.get(true);      // Ace인 카드들
        List<Card> nonAceCards = partitioned.get(false);// Ace가 아닌 카드들
        int totalScoreNonAce = nonAceCards.stream()
                .mapToInt(Card::getNumber)
                .sum();

        return addAceScore(aceCards, totalScoreNonAce);
    }

    public boolean calculateBust(int totalScore) {
        return totalScore > 21;
    }

    int addAceScore(List<Card> aceCards, int totalScore) {
        for (int i = 0; i < aceCards.size(); i++) {
            if (totalScore + 11 > 21) {
                totalScore += 1;
                continue;
            }
            totalScore += 11;
        }

        return totalScore;
    }
}