package blackjack.domain.card;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static blackjack.domain.card.Deck.CARD_TOTAL_SIZE;

public class CardFactory {

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
            throw new IllegalArgumentException(String.format("카드의 개수는 총 %d 개여야 합니다.", CARD_TOTAL_SIZE));
        }
    }
}
