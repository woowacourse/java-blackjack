package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {

    @Test
    @DisplayName("카드를 추가하면 크기가 1 증가한다.")
    void addCard() {
        Cards cards = new Cards();
        int expectedSize = cards.getCount() + 1;

        cards.add(Card.of(Symbol.SPADE, Number.ACE));

        assertThat(cards.getCount()).isEqualTo(expectedSize);
    }

    @ParameterizedTest
    @MethodSource("generateCards")
    @DisplayName("보유한 카드의 점수 합을 계산한다.")
    void getSum(List<Card> createdCards, int expectedSum) {
        Cards cards = new Cards();

        for (Card card : createdCards) {
            cards.add(card);
        }

        assertThat(cards.sum()).isEqualTo(expectedSum);
    }

    static Stream<Arguments> generateCards() {
        return Stream.of(
                Arguments.of(List.of(Card.of(Symbol.SPADE, Number.SEVEN), Card.of(Symbol.CLOVER, Number.TWO)), 9),
                Arguments.of(List.of(Card.of(Symbol.HEART, Number.NINE), Card.of(Symbol.HEART, Number.TWO)), 11),
                Arguments.of(List.of(Card.of(Symbol.DIAMOND, Number.TEN), Card.of(Symbol.SPADE, Number.TEN)), 20),
                Arguments.of(List.of(Card.of(Symbol.CLOVER, Number.THREE), Card.of(Symbol.CLOVER, Number.TWO)), 5),
                Arguments.of(List.of(Card.of(Symbol.CLOVER, Number.SEVEN), Card.of(Symbol.SPADE, Number.SEVEN), Card.of(Symbol.DIAMOND, Number.SEVEN)), 21),
                Arguments.of(List.of(Card.of(Symbol.CLOVER, Number.TEN), Card.of(Symbol.SPADE, Number.ACE), Card.of(Symbol.DIAMOND, Number.SEVEN)), 28)
        );
    }

    @ParameterizedTest
    @MethodSource("generateCardsWithACE")
    @DisplayName("에이스가 포함된 카드의 줌수 합을 계산한다.")
    void getSumWithACE(List<Card> createdCards, int expectedCount) {
        Cards cards = new Cards();

        for (Card card : createdCards) {
            cards.add(card);
        }

        assertThat(cards.getAceCount()).isEqualTo(expectedCount);
    }

    static Stream<Arguments> generateCardsWithACE() {
        return Stream.of(
                Arguments.of(List.of(Card.of(Symbol.SPADE, Number.ACE), Card.of(Symbol.CLOVER, Number.ACE)), 2),
                Arguments.of(List.of(Card.of(Symbol.SPADE, Number.ACE), Card.of(Symbol.HEART, Number.ACE), Card.of(Symbol.CLOVER, Number.ACE)), 3),
                Arguments.of(List.of(Card.of(Symbol.SPADE, Number.ACE), Card.of(Symbol.HEART, Number.ACE), Card.of(Symbol.CLOVER, Number.ACE), Card.of(Symbol.DIAMOND, Number.ACE)), 4)
        );
    }
}
