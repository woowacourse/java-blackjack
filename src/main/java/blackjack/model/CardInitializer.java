package blackjack.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CardInitializer {

    private CardInitializer() {}

    public static Cards createCardDeck() {
        return new Cards(initializeCardDeck());
    }

    private static List<Card> initializeCardDeck() {
        List<Card> cards = new ArrayList<>();
        Arrays.stream(CardType.values())
                .forEach(cardType -> Arrays.stream(CardNumber.values())
                        .forEach(
                                cardNumber -> cards.add(new Card(cardType, cardNumber))
                        )
                );
        return cards;
    }

}
