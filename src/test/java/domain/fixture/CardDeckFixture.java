package domain.fixture;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardShape;
import domain.card.CardValue;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CardDeckFixture {

    public static CardDeck cardDeck(final CardValue... cardValues) {
        final List<Card> collect = Arrays.stream(cardValues)
                .map(it -> new Card(CardShape.DIAMOND, it))
                .collect(toList());
        return CardDeck.shuffledFullCardDeck(new NoneCardShuffler(collect));
    }
}
