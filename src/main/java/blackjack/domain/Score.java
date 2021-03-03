package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Score {

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
//        int totalScore = cards.stream()
//                .filter(card -> card.getDenomination() != Denomination.ACE)
//                .mapToInt(card -> card.getDenomination().getScore())
//                .reduce(0, Integer::sum);
//
//        int a = cards.stream()
//                .filter(card -> card.getDenomination() == Denomination.ACE)
//                .mapToInt(card -> card.getDenomination().getScore())
//                .reduce(0, (score1, score2) -> aceCalculator(totalScore) + totalScore);

        int score = 0;
        for (Card card : cards) {
            if (card.getDenomination() == Denomination.ACE) {
                if (score + 11 > 21) {
                    score += card.getDenomination().getScore();
                } else {
                    score += 11;
                }
            } else
                score += card.getDenomination().getScore();
        }
        return score;
    }

//    private static int aceCalculator(int totalScore) {
//        if (totalScore < 11) {
//            return 11;
//        }
//        return 1;
//    }


//    private static int calculator1(List<Card> cards) {
//        int score = cards.stream()
//                .map(Card::getDenomination)
//                .mapToInt(Denomination::getScore)
//                .sum();
//
//        int notIncludeAceScore = cards.stream()
//                .filter(card -> !card.isAce())
//                .map(Card::getDenomination)
//                .mapToInt(Denomination::getScore)
//                .sum();
//
//        int onlyAceScore = cards.stream()
//                .filter(Card::isAce)
//                .map(Card::getDenomination)
//                .mapToInt(Denomination::getScore)
//                .sum();
//    }
}
