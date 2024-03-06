package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Deck {
    private final List<Card> cards;

    private Deck(List<Card> cards) {
        validateDuplicateCard(cards);
        this.cards = cards;
    }

    public static Deck of(Card... cards) {
        List<Card> tmpCards = new ArrayList<>(List.of(cards));
        return new Deck(tmpCards);
    }

    public static Deck fullDeck() {
        List<Card> cards = new ArrayList<>();
        for (CardType cardType : CardType.values()) {
            for (CardName cardName : CardName.values()) {
                Card card = new Card(cardName, cardType);
                cards.add(card);
            }
        }
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
