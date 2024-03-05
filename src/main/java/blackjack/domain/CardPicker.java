package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class CardPicker {
    private final Random random;

    private final List<Card> cards;

    public CardPicker(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
        random = new Random();
    }

    private Card pick() {
        int randomIndex = random.nextInt(0, cards.size());
        return cards.remove(randomIndex);
    }

    public List<Card> pick(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> pick())
                .toList();
    }
}
