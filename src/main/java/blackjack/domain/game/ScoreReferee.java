package blackjack.domain.game;

import static java.util.stream.Collectors.toList;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import java.util.List;

public class ScoreReferee {

    private ScoreReferee() {
        
    }

    public static int calculateScore(final List<Card> cards) {

        int sum = getSum(cards);
        final List<Card> acePack = getAcePack(cards);

        while (sum > 21 && !acePack.isEmpty()) {
            sum -= 10;
            acePack.remove(0);
        }

        if (sum > 21) {
            return -1;
        }

        return sum;
    }

    private static List<Card> getAcePack(final List<Card> cards) {
        return cards.stream()
                .filter(card -> CardNumber.ACE.equals(card.getNumber()))
                .collect(toList());
    }

    private static int getSum(final List<Card> cards) {
        return cards.stream()
                .map(Card::getNumber)
                .mapToInt(CardNumber::getScore)
                .sum();
    }
}
