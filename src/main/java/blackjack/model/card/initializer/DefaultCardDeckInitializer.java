package blackjack.model.card.initializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardType;
import blackjack.model.card.Cards;

public class DefaultCardDeckInitializer implements CardDeckInitializer {

    @Override
    public Cards initialize() {
        return new Cards(initializeShuffledCardDeck());
    }

    private List<Card> initializeShuffledCardDeck() {
        return Arrays.stream(CardType.values())
                .flatMap(cardType -> Arrays.stream(CardNumber.values())
                        .map(cardNumber -> new Card(cardType, cardNumber))
                ).collect(Collectors.collectingAndThen(Collectors.toCollection(ArrayList::new),
                        list -> {
                            Collections.shuffle(list);
                            return list;
                        })
                );
    }

}
