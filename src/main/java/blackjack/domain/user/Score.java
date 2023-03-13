package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Score {

    private static final int MAX_NUMBER = 21;
    private static final int BUST_NUMBER = -1;
    private static final int ACE_PARSING_NUMBER = 10;

    private int value;

    public void calculateScore(Cards cards) {
        final List<Card> acePack = getAcePack(cards);
        int sum = calculateAceSum(getSum(cards), acePack);

        if (sum > MAX_NUMBER) {
            sum = BUST_NUMBER;
        }

        this.value = sum;
    }

    private List<Card> getAcePack(Cards cards) {
        return cards.getCards().stream()
                .filter(Card::isAce)
                .collect(toList());
    }

    private int getSum(Cards cards) {
        return cards.getCards().stream()
                .map(Card::getNumber)
                .mapToInt(CardNumber::getScore)
                .sum();
    }

    private int calculateAceSum(final int sum, final List<Card> acePack) {
        int result = sum;
        while (result > MAX_NUMBER && !acePack.isEmpty()) {
            result -= ACE_PARSING_NUMBER;
            acePack.remove(acePack.size() - 1);
        }
        return result;
    }

    public int getValue() {
        return value;
    }

    public boolean isBust() {
        return value == BUST_NUMBER;
    }
}
