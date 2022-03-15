package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {

    @ParameterizedTest(name = "[{index}] {0}인 경우 총합은 {1}")
    @MethodSource("provideParameters")
    @DisplayName("단일 카드 총합 구하기")
    void sum(Number number, int expect) {
        // given
        Cards cards = new Cards(Collections.singletonList(new Card(number, Suit.CLOVER)));

        // then
        assertThat(cards.sum()).isEqualTo(expect);
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.arguments(Number.ACE, 11),
                Arguments.arguments(Number.TWO, 2),
                Arguments.arguments(Number.THREE, 3),
                Arguments.arguments(Number.FOUR, 4),
                Arguments.arguments(Number.FIVE, 5),
                Arguments.arguments(Number.SIX, 6),
                Arguments.arguments(Number.SEVEN, 7),
                Arguments.arguments(Number.EIGHT, 8),
                Arguments.arguments(Number.NINE, 9),
                Arguments.arguments(Number.TEN, 10),
                Arguments.arguments(Number.JACK, 10),
                Arguments.arguments(Number.QUEEN, 10),
                Arguments.arguments(Number.KING, 10)
        );
    }

    @Test
    @DisplayName("ACE 2개인 경우")
    void sum2() {
        // given
        Cards cards = new Cards(Arrays.asList(new Card(Number.ACE, Suit.CLOVER)
                , new Card(Number.ACE, Suit.HEART)));

        // then
        assertThat(cards.sum()).isEqualTo(12);
    }

    @Test
    @DisplayName("카드 추가하기")
    void add() {
        // given
        List<Card> list = new ArrayList<>();
        list.add(new Card(Number.ACE, Suit.CLOVER));
        Cards cards = new Cards(list);
        Card newCard = new Card(Number.JACK, Suit.HEART);

        // when
        cards.add(newCard);

        // then
        assertThat(cards.getValue()).contains(newCard);
    }
}
