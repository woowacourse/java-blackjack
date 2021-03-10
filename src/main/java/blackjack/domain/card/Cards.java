package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cards {
    private final List<Card> cards;
    private int index = 0;

    private Cards(List<Card> cards) {
        this.cards = cards;
    }

    public static Cards createNormalCards() {
        List<Card> cards =
            Arrays.stream(Symbol.values())
                .flatMap(Cards::createCard)
                .collect(Collectors.toList());
        return new Cards(cards);
    }

    private static Stream<Card> createCard(Symbol symbol) {
        return Arrays.stream(CardNumber.values())
            .map(cardNumber -> Card.of(cardNumber, symbol));
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card next() {
        return cards.get(index++);
    }
}
