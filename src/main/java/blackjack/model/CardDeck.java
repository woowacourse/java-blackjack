package blackjack.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CardDeck {

    private static final int CARD_DECK_SIZE = 52;

    private final List<Card> cards;

    private CardDeck(List<Card> cards) {
        if (cards.isEmpty()) {
            throw new IllegalStateException("cards가 null입니다.");
        }
        this.cards = cards;
    }

    public static CardDeck init() {
        List<Card> cards = new ArrayList<>();

        Arrays.stream(Suit.values())
                .forEach(suit -> Arrays.stream(Rank.values())
                        .forEach(rank -> cards.add(new Card(rank, suit))));

        if (cards.size() != CARD_DECK_SIZE) {
            throw new IllegalStateException("덱이 잘 못 생성됐습니다.");
        }

        return new CardDeck(cards);
    }

    public Card draw() {
        // 카드를 덱에서 1장 드로우 한다.
        Random random = new Random();
        int randomIndex = random.nextInt(0, cards.size());

        return cards.get(randomIndex);
    }
}
