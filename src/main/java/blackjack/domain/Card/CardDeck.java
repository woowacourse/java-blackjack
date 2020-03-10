package blackjack.domain.Card;

import blackjack.domain.Player;

import java.util.HashSet;
import java.util.Set;

public class CardDeck {
    private static final String NULL_ERROR_MSG = "카드를 찾을 수 없습니다.";
    private final Set<Card> cards;

    public CardDeck() {
        cards = new HashSet<>();
        for (CardNumber cardNumber : CardNumber.values()) {
            for (Figure figure : Figure.values()) {
                cards.add(new Card(cardNumber, figure));
            }
        }
    }

    public int getSize() {
        return cards.size();
    }

    public Card of(CardNumber number, Figure figure) {
        return cards.stream()
                .filter(card -> card.has(number, figure))
                .findFirst().orElseThrow(() ->new IllegalArgumentException(NULL_ERROR_MSG));
    }

    public Card giveCard() {
        Card card = cards.stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NULL_ERROR_MSG));
        cards.remove(card);
        return card;
    }
}
