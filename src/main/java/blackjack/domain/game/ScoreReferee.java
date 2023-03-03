package blackjack.domain.game;

import static java.util.stream.Collectors.toList;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import java.util.List;

public class ScoreReferee {

    public static final int MAX_NUMBER = 21;
    public static final int ACE_PARSING_NUMBER = 10;
    public static final int BUST_NUMBER = -1;

    private ScoreReferee() {

    }

    public static int calculateScore(final List<Card> cards) {
        int sum = getSum(cards);
        final List<Card> acePack = getAcePack(cards);

        while (sum > MAX_NUMBER && !acePack.isEmpty()) {
            sum -= ACE_PARSING_NUMBER;
            acePack.remove(0);
        }

        if (sum > MAX_NUMBER) {
            return BUST_NUMBER;
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
