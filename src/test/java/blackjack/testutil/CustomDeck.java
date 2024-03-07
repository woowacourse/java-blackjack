package blackjack.testutil;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

public class CustomDeck implements Deck {
    private final Iterator<Card> cards;

    public CustomDeck(List<Number> numbers, List<Shape> shapes) {
        if (shapes.size() != numbers.size()) {
            throw new IllegalArgumentException("모양과 숫자의 개수가 일치하지 않습니다.");
        }
        cards = IntStream.range(0, shapes.size())
                .mapToObj((index) ->
                        new Card(numbers.get(index), shapes.get(index)))
                .iterator();
    }

    public CustomDeck(List<Number> numbers) {
        cards = numbers.stream()
                .map(number -> new Card(number, Shape.HEART))
                .iterator();
    }

    @Override
    public Card drawCard() {
        if (!cards.hasNext()) {
            throw new IllegalArgumentException("더 이상 뽑을 수 있는 카드가 없습니다.");
        }
        return cards.next();
    }
}
