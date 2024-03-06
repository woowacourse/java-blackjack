package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardsTest {

    static Stream<Arguments> hasAce() {
        return Stream.of(
                Arguments.of(new Cards(), false),
                Arguments.of(new Cards(List.of(new Card(CardShape.SPADE, CardNumber.THREE))), false),
                Arguments.of(new Cards(List.of(new Card(CardShape.SPADE, CardNumber.ACE))), true)
        );
    }

    @DisplayName("에이스가 포함되었는지 알 수 있다.")
    @MethodSource
    @ParameterizedTest
    void hasAce(Cards cards, boolean expected) {
        assertThat(cards.hasAce()).isEqualTo(expected);
    }
}
