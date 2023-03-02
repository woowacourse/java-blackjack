package blackjack.domain.card;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CardFactory {
    private static final int CARD_TOTAL_SIZE = 48;

    public static List<Card> of() {
        List<Card> cards = Arrays.stream(CardSymbol.values())
                .flatMap(cardSymbol -> Arrays.stream(CardNumber.values())
                        .map(cardNumber -> new Card(cardNumber, cardSymbol)))
                .collect(Collectors.toList());
        validate(cards);
        return cards;
    }

    private static void validate(List<Card> cards) {
        if (cards.size() != CARD_TOTAL_SIZE) {
            throw new IllegalArgumentException("카드의 개수는 총 48개여야 합니다.");
        }
    }
}
