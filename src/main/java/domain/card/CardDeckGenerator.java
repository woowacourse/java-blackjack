package domain.card;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CardDeckGenerator {

    private CardDeckGenerator() {
        throw new IllegalStateException("생성할 수 없는 객체입니다.");
    }

    public static CardDeck create() {
        List<Card> cards = Arrays.stream(CardType.values())
                .flatMap(CardDeckGenerator::createCard)
                .collect(toList());

        return CardDeck.createShuffled(cards);
    }

    private static Stream<Card> createCard(CardType cardType) {
        return Arrays.stream(CardValue.values())
                .map(cardValue -> new Card(cardType, cardValue));
    }
}
