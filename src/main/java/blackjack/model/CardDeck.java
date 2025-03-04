package blackjack.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardDeck {

    private final List<Card> cardDeck;

    public CardDeck() {
        cardDeck = initializeCardDeck();
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

    public List<Card> getCardDeck() {
        return Collections.unmodifiableList(cardDeck);
    }

}
