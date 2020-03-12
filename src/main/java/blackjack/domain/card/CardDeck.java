package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CardDeck {
    private static final String DUPLICATE_ERROR_MSG = "중복되는 카드가 존재합니다.";
    private static final String NO_CARD_ERROR_MSG = "카드를 찾을 수 없습니다.";
    private static final String CARD_EMPTY_ERROR_MSG = "카드를 모두 사용했습니다.";
    public static final int FIRST_INDEX = 1;
    private final List<Card> cards;

    public CardDeck() {
        cards = new ArrayList<>();
        for (CardNumber cardNumber : CardNumber.values()) {
            for (CardFigure cardFigure : CardFigure.values()) {
                cards.add(new Card(cardNumber, cardFigure));
            }
        }
        validateNotDuplicate(cards);
        Collections.shuffle(cards);
    }

    private void validateNotDuplicate(List<Card> cards) {
        List<Card> distinctCards = cards.stream()
                .distinct()
                .collect(Collectors.toList());
        if (distinctCards.size() != cards.size()) {
            throw new IllegalArgumentException(DUPLICATE_ERROR_MSG);
        }
    }

    public int getSize() {
        return cards.size();
    }

    public Card getCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException(CARD_EMPTY_ERROR_MSG);
        }
        Card card = cards.get(FIRST_INDEX);
        cards.remove(card);
        return card;
    }

    public Card getCard(CardNumber number, CardFigure cardFigure) {
        return cards.stream()
                .filter(card -> card.has(number, cardFigure))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_CARD_ERROR_MSG));
    }
}
