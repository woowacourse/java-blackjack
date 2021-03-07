package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Score {

    public static final int MAX_SCORE = 21;

    private Score() {
    }

    public static int calculatorScore(Cards cards) {
        return calculator(cards.getCards());
    }

    private static int calculator(List<Card> cards) {
        return aceCardScore(cards, noneAceCardScore(cards));
    }

    private static int noneAceCardScore(List<Card> cards) {
        return cards.stream()
                .filter(card -> !Denomination.isAce(card.getDenomination()))
                .mapToInt(card -> Denomination.getScore(card.getDenomination()))
                .sum();
    }

    private static int aceCardScore(List<Card> cards, int score) {
        int aceCount = (int) cards
                .stream()
                .filter(card -> Denomination.isAce(card.getDenomination()))
                .count();
        for (int i = 0; i < aceCount; i++) {
            score += Denomination.selectAceScore(score);
        }

        return score;
    }
}
