package blackjack.model.card.initializer;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardType;
import blackjack.model.card.Cards;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DefaultCardDeckInitializer implements CardDeckInitializer {

    @Override
    public Cards initialize() {
        return new Cards(initializeCardDeck());
    }

    private List<Card> initializeCardDeck() {
        List<Card> cards = new ArrayList<>();
        Arrays.stream(CardType.values())
                .forEach(cardType -> Arrays.stream(CardNumber.values())
                        .forEach(
                                cardNumber -> cards.add(new Card(cardType, cardNumber))
                        )
                );
        Collections.shuffle(cards);
        return cards;
    }
}
