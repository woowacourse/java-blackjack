package blackjack.model;

import static blackjack.model.Constant.ACE_SCORE_ELEVEN;
import static blackjack.model.Constant.ACE_SCORE_ONE;
import static blackjack.model.Constant.INIT_CARDS_END_IDX;
import static blackjack.model.Constant.INIT_CARDS_START_IDX;
import static blackjack.model.Constant.TWENTY_ONE;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CardCalculator {

    public boolean calculateBlackjack(List<Card> cards) {
        List<Card> initCards = cards.subList(INIT_CARDS_START_IDX, INIT_CARDS_END_IDX);
        return totalScore(initCards) == TWENTY_ONE;
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
        return totalScore > TWENTY_ONE;
    }

    int addAceScore(List<Card> aceCards, int totalScore) {
        for (int i = 0; i < aceCards.size(); i++) {
            if (totalScore + ACE_SCORE_ELEVEN > TWENTY_ONE) {
                totalScore += ACE_SCORE_ONE;
                continue;
            }
            totalScore += ACE_SCORE_ELEVEN;
        }

        return totalScore;
    }
}