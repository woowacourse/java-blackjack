package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Cards {

    private static final int MAX_NUMBER = 21;
    private static final int BUST_NUMBER = -1;
    private static final int ACE_PARSING_NUMBER = 10;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        final List<Card> acePack = getAcePack();
        int sum = calculateAceSum(getSum(), acePack);

        if (sum > MAX_NUMBER) {
            return BUST_NUMBER;
        }

        return sum;
    }

    private int getSum() {
        return cards.stream()
                .map(Card::getNumber)
                .mapToInt(CardNumber::getScore)
                .sum();
    }

    private List<Card> getAcePack() {
        return cards.stream()
                .filter(Card::isAce)
                .collect(toList());
    }

    private int calculateAceSum(final int sum, final List<Card> acePack) {
        int result = sum;
        while (result > MAX_NUMBER && !acePack.isEmpty()) {
            result -= ACE_PARSING_NUMBER;
            acePack.remove(acePack.size() - 1);
        }
        return result;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
