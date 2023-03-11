package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {
    private static final int BLACK_JACK = 21;
    private static final int DECREASE_ACE_VALUE = -10;

    private List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int getSum() {
        Integer sum = cards.stream()
                .map(s -> s.getCardValue())
                .collect(Collectors.summingInt(Integer::intValue));
        return calculateAceValue(sum);
    }

    private int calculateAceValue(int sum) {
        int countAce = countAce();
        while (countAce-- > 0 && sum > BLACK_JACK) {
            sum += DECREASE_ACE_VALUE;
        }
        return sum;
    }

    public int countAce() {
        return Math.toIntExact(cards.stream().filter(s -> s.isAce()).count());
    }

    public boolean isOverBlackJack() {
        return getSum() > BLACK_JACK;
    }

    public boolean isBlackJack() {
        return getSum() == BLACK_JACK && cards.size() == 2;
    }

    public List<Card> getCards() {
        return cards;
    }

}
