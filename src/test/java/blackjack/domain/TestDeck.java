package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Shape;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestDeck implements Deck {
    private final List<Card> cards;
    int count = 0;

    public TestDeck(List<Integer> cardNumbers) {
        this.cards = cardNumbers.stream()
                .map(num -> new Card(Shape.HEART, NumberByInt.getNumberOf(num)))
                .collect(Collectors.toList());
    }

    @Override
    public Card drawCard() {
        if (count < cards.size()) {
            final Card card = cards.get(count);
            count++;
            return card;
        }
        return new Card(Shape.HEART, CardNumber.ACE);
    }

    private enum NumberByInt {
        ACE(CardNumber.ACE, 1),
        Two(CardNumber.TWO, 2),
        THREE(CardNumber.THREE, 3),
        FOUR(CardNumber.FOUR, 4),
        FIVE(CardNumber.FIVE, 5),
        SIX(CardNumber.SIX, 6),
        SEVEN(CardNumber.SEVEN, 7),
        EIGHT(CardNumber.EIGHT, 8),
        NINE(CardNumber.NINE, 9),
        TEN(CardNumber.TEN, 10),
        JACK(CardNumber.JACK, 11),
        QUEEN(CardNumber.JACK, 12),
        KING(CardNumber.KING, 13);

        private CardNumber cardNumber;
        private int value;

        NumberByInt(CardNumber cardNumber, int value) {
            this.cardNumber = cardNumber;
            this.value = value;
        }

        public static CardNumber getNumberOf(int number) {
            final NumberByInt returnValue = Arrays.stream(values())
                    .filter((target) -> target.value == number)
                    .findAny()
                    .orElseThrow(() -> {
                        throw new IllegalArgumentException("잘못된 카드 순서 입력");
                    });
            return returnValue.cardNumber;
        }
    }

}
