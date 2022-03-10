package domain.card;

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
    void sum(Denomination denomination, int expect) {
        // given
        Cards cards = new Cards(Collections.singletonList(new Card(denomination, Suit.CLOVER)));

        // then
        assertThat(cards.sum()).isEqualTo(expect);
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.arguments(Denomination.ACE, 11),
                Arguments.arguments(Denomination.TWO, 2),
                Arguments.arguments(Denomination.THREE, 3),
                Arguments.arguments(Denomination.FOUR, 4),
                Arguments.arguments(Denomination.FIVE, 5),
                Arguments.arguments(Denomination.SIX, 6),
                Arguments.arguments(Denomination.SEVEN, 7),
                Arguments.arguments(Denomination.EIGHT, 8),
                Arguments.arguments(Denomination.NINE, 9),
                Arguments.arguments(Denomination.TEN, 10),
                Arguments.arguments(Denomination.JACK, 10),
                Arguments.arguments(Denomination.QUEEN, 10),
                Arguments.arguments(Denomination.KING, 10)
        );
    }

    @Test
    @DisplayName("ACE 2개인 경우")
    void sum2() {
        // given
        Cards cards = new Cards(Arrays.asList(new Card(Denomination.ACE, Suit.CLOVER)
                , new Card(Denomination.ACE, Suit.HEART)));

        // then
        assertThat(cards.sum()).isEqualTo(12);
    }

    @Test
    @DisplayName("카드 추가하기")
    void add() {
        // given
        List<Card> list = new ArrayList<>();
        list.add(new Card(Denomination.ACE, Suit.CLOVER));
        Cards cards = new Cards(list);
        Card newCard = new Card(Denomination.JACK, Suit.HEART);

        // when
        cards.add(newCard);

        // then
        assertThat(cards.getValue()).contains(newCard);
    }
}
