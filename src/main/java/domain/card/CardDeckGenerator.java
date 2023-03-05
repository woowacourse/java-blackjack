package domain.card;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

public class CardDeckGenerator {

    private CardDeckGenerator() {
        throw new IllegalStateException("생성할 수 없는 객체입니다.");
    }

    public static CardDeck create() {
        List<Card> cards = Arrays.stream(CardType.values())
                .flatMap(cardType ->
                        Arrays.stream(CardValue.values())
                                .map(cardValue -> new Card(cardType, cardValue)))
                .collect(toList());

        return CardDeck.createShuffled(cards);
    }
}
