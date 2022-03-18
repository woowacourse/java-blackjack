package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.TestUtil.CLOVER_ACE;
import static utils.TestUtil.CLOVER_EIGHT;
import static utils.TestUtil.CLOVER_FIVE;
import static utils.TestUtil.CLOVER_FOUR;
import static utils.TestUtil.CLOVER_JACK;
import static utils.TestUtil.CLOVER_KING;
import static utils.TestUtil.CLOVER_NINE;
import static utils.TestUtil.CLOVER_QUEEN;
import static utils.TestUtil.CLOVER_SEVEN;
import static utils.TestUtil.CLOVER_SIX;
import static utils.TestUtil.CLOVER_TEN;
import static utils.TestUtil.CLOVER_THREE;
import static utils.TestUtil.CLOVER_TWO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CardsTest {

    @ParameterizedTest(name = "[{index}] {0}인 경우 총합은 {1}")
    @MethodSource("provideParameters")
    @DisplayName("단일 카드 총합 구하기")
    void sum(Card card, int expect) {
        // given
        Cards cards = new Cards(Collections.singletonList(card));

        // then
        assertThat(cards.sum()).isEqualTo(expect);
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.arguments(CLOVER_ACE, 1),
                Arguments.arguments(CLOVER_TWO, 2),
                Arguments.arguments(CLOVER_THREE, 3),
                Arguments.arguments(CLOVER_FOUR, 4),
                Arguments.arguments(CLOVER_FIVE, 5),
                Arguments.arguments(CLOVER_SIX, 6),
                Arguments.arguments(CLOVER_SEVEN, 7),
                Arguments.arguments(CLOVER_EIGHT, 8),
                Arguments.arguments(CLOVER_NINE, 9),
                Arguments.arguments(CLOVER_TEN, 10),
                Arguments.arguments(CLOVER_JACK, 10),
                Arguments.arguments(CLOVER_QUEEN, 10),
                Arguments.arguments(CLOVER_KING, 10)
        );
    }

    @Test
    @DisplayName("카드 추가하기")
    void add() {
        // given
        List<Card> list = new ArrayList<>();
        list.add(CLOVER_ACE);
        Cards cards = new Cards(list);
        Card newCard = CLOVER_JACK;

        // when
        cards.add(newCard);

        // then
        assertThat(cards.getValue()).contains(newCard);
    }
}
