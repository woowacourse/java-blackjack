package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {
    private List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public int getSum() {
        Integer sum = cards.stream()
                .map(s -> s.getCardValue())
                .collect(Collectors.summingInt(Integer::intValue));

        int countAce = countAce();
        while (countAce-- > 0 && sum > 21) {
            sum -= 10;
        }

        return sum;
    }

    public int countAce() {
        return Math.toIntExact(cards.stream().filter(s -> s.isAce()).count());
    }

    public List<String> getCards() {
        return cards.stream()
                .map(Card::getCard)
                .collect(Collectors.toList());
    }

}
