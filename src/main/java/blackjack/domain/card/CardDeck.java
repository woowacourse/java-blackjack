package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CardDeck {

    private final List<Card> cards;

    public CardDeck(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public CardDeck() {
        this(initCards());
    }

    private static List<Card> initCards() {
        List<Card> cards = Arrays.stream(Pattern.values())
                .map(CardDeck::createCardsBy)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        Collections.shuffle(cards);
        return cards;
    }

    private static List<Card> createCardsBy(Pattern pattern) {
        return Arrays.stream(Denomination.values())
                .map(denomination -> new Card(pattern, denomination))
                .collect(Collectors.toList());
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("[ERROR] 카드 덱이 비어 있습니다.");
        }
        Card card = cards.get(0);
        cards.remove(0);
        return card;
    }

    public List<Card> drawDouble() {
        return new ArrayList<>(List.of(draw(), draw()));
    }
}
