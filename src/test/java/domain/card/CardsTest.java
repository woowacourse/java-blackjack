package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import static domain.card.CardGenerator.cardOf;
import static domain.card.CardRank.ACE;
import static domain.card.CardRank.THREE;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardsTest {

    static Stream<Arguments> hasAce() {
        return Stream.of(
                Arguments.of(Cards.create(), false),
                Arguments.of(Cards.from(List.of(cardOf(THREE))), false),
                Arguments.of(Cards.from(List.of(cardOf(ACE))), true)
        );
    }

    @DisplayName("에이스가 포함되었는지 알 수 있다.")
    @MethodSource
    @ParameterizedTest
    void hasAce(Cards cards, boolean expected) {
        assertThat(cards.hasAce()).isEqualTo(expected);
    }

    static Stream<Arguments> score() {
        return Stream.of(
                Arguments.of(Cards.create(), false, 0),
                Arguments.of(Cards.from(List.of(cardOf(THREE))), false, 3),
                Arguments.of(Cards.from(List.of(cardOf(ACE))), false, 1),
                Arguments.of(Cards.from(List.of(cardOf(ACE))), true, 11)
        );
    }

    @DisplayName("점수를 계산할 수 있다.")
    @MethodSource
    @ParameterizedTest
    void score(Cards cards, boolean isSoft, int expected) {
        assertThat(cards.score(isSoft)).isEqualTo(expected);
    }
}
