package blackjack.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardDeck {

    private final List<Card> cards;

    public CardDeck() {
        cards = initializeCardDeck();
    }

    private List<Card> initializeCardDeck() {
        List<Card> cards = new ArrayList<>();
        Arrays.stream(CardType.values())
                .forEach(cardType -> Arrays.stream(CardNumber.values())
                        .forEach(
                                cardNumber -> cards.add(new Card(cardType, cardNumber))
                        )
                );
        return cards;
    }

    public List<Card> draw(final int drawSize) {
        Collections.shuffle(cards);
        List<Card> result = List.copyOf(cards.subList(0, drawSize));
        cards.removeAll(result);
        return result;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

}
