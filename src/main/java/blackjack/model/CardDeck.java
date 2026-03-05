package blackjack.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CardDeck {

    private final List<Card> cards;

    private CardDeck(List<Card> cards) {
        this.cards = cards;
    }

    public static CardDeck init() {
        List<Card> cards = new ArrayList<>();

        Arrays.stream(Suit.values())
                .forEach(suit -> Arrays.stream(Rank.values())
                        .forEach(rank -> cards.add(new Card(rank, suit))));

        return new CardDeck(cards);
    }

    public Card draw() {
        // 카드를 덱에서 1장 드로우 한다.
        Random random = new Random();
        int randomIndex = random.nextInt(0, cards.size());

        return cards.get(randomIndex);
    }
}
