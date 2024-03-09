package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Deck {
    private final List<Card> cards;

    private Deck(List<Card> cards) {
        validateDuplicateCard(cards);
        this.cards = new ArrayList<>(cards);
    }

    public static Deck of(Card... cards) {
        return new Deck(List.of(cards));
    }

    public static Deck fullDeck() {
        List<Card> cards = Arrays.stream(CardType.values())
                .flatMap(cardType -> Arrays.stream(CardName.values())
                        .map(cardName -> new Card(cardName, cardType)))
                .collect(Collectors.toList());
        return new Deck(cards);
    }

    private void validateDuplicateCard(List<Card> cards) {
        Set<Card> cardSet = new HashSet<>(cards);
        if (cardSet.size() != cards.size()) {
            throw new IllegalArgumentException("중복되는 카드가 있습니다.");
        }
    }

    public Card draw(CardDrawStrategy cardDrawStrategy) {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("덱이 비어있습니다.");
        }

        Card card = cardDrawStrategy.nextCard(cards);
        cards.remove(card);

        return card;
    }
}
