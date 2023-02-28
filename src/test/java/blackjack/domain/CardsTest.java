package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class CardsTest {

    @Test
    @DisplayName("카드를 추가하면 크기가 1 증가한다.")
    void addCard() {
        Cards cards = new Cards();
        int expectedSize = cards.getCards().size() + 1;

        cards.add(new Card(Symbol.SPADE, Number.ACE));

        assertThat(cards.getCards().size()).isEqualTo(expectedSize);
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
                Arguments.of(List.of(new Card(Symbol.SPADE, Number.SEVEN), new Card(Symbol.CLOVER, Number.TWO)), 9),
                Arguments.of(List.of(new Card(Symbol.HEART, Number.NINE), new Card(Symbol.HEART, Number.TWO)), 11),
                Arguments.of(List.of(new Card(Symbol.DIAMOND, Number.TEN), new Card(Symbol.SPADE, Number.TEN)), 20),
                Arguments.of(List.of(new Card(Symbol.CLOVER, Number.THREE), new Card(Symbol.CLOVER, Number.TWO)), 5)
        );
    }
}
