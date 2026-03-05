package blackjack.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards; // 52장을 가지고 있음

    public Deck() {
        this.cards = createDeck();
    }

    public List<Card> createDeck() {
        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < Figure.getSize(); i++) {
            int index = i;
            // TODO: 코드 가독성 올리기 (2026. 3. 5.)
            cards.addAll(Arrays.stream(Number.values())
                    .map(num -> new Card(Figure.values()[index], num))
                    .toList());
        }
        return cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card giveCard() {
        return cards.removeFirst();
    }

    public int getSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }
}
