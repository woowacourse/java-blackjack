package blackjack.domain.card;

import blackjack.domain.user.User;

import java.util.HashSet;
import java.util.Set;

public class CardDeck {
    private static final String NULL_ERROR_MSG = "카드를 찾을 수 없습니다.";
    private final Set<Card> cards;

    public CardDeck() {
//        cards = Arrays.stream(CardNumber.values())
//                .map(cardNumber ->
//                        Arrays.stream(CardFigure.values()).map(cardFigure -> {
//                    return new Card (cardNumber, cardFigure);
//                })).collect(Collectors.toSet());

        cards = new HashSet<>();
        for (CardNumber cardNumber : CardNumber.values()) {
            for (CardFigure cardFigure : CardFigure.values()) {
                cards.add(new Card(cardNumber, cardFigure));
            }
        }
    }

    public int getSize() {
        return cards.size();
    }

    public Card of(CardNumber number, CardFigure cardFigure) {
        return cards.stream()
                .filter(card -> card.has(number, cardFigure))
                .findFirst()
                .orElseThrow(() ->new IllegalArgumentException(NULL_ERROR_MSG));
    }

    public Card getCard() {
        Card card = cards.stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NULL_ERROR_MSG));
        cards.remove(card);
        return card;
    }
}
