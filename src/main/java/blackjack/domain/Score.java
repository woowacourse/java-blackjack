package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Score {

    public static final int maxScore = 21;
    private Score() {
    }

    public static int calculatorScore(Cards cards) {
        return calculator(sortCards(cards));
    }

    private static List<Card> sortCards(Cards cards) {
        List<Card> result = new ArrayList<>(cards.getCards());
        Collections.sort(result);
        return result;
    }

    private static int calculator(List<Card> cards) {
        int score = 0;
        for (Card card : cards) {
            if (card.getDenomination() == Denomination.ACE) {
                if (score + 11 > maxScore) {
                    score += card.getDenomination().getScore();
                } else {
                    score += 11;
                }
            } else
                score += card.getDenomination().getScore();
        }
        return score;
    }
}
