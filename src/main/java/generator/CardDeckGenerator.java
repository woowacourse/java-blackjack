package generator;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.Type;
import domain.card.Value;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CardDeckGenerator {

    private CardDeckGenerator() {
        throw new IllegalStateException("생성할 수 없는 객체입니다.");
    }

    public static CardDeck create() {
        List<Card> cards = Arrays.stream(Type.values())
                .flatMap(type -> Arrays.stream(Value.values())
                        .map(value -> new Card(type, value)))
                .collect(Collectors.toList());

        return CardDeck.createShuffled(cards);
    }
}
