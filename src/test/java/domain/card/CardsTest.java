package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CardsTest {

    @Test
    @DisplayName("카드 덱을 생성할 수 있다.")
    void deck() {
        Cards deck = Cards.deck();
        assertThat(deck.toList()
                       .size()).isEqualTo(52);
    }

    @Test
    @DisplayName("카드 숫자의 합을 구할 수 있다.")
    void sum() {
        Cards cards = new Cards(List.of(new Card(CardShape.SPADE, CardNumber.KING), new Card(CardShape.SPADE, CardNumber.THREE)));
        assertThat(cards.sum()).isEqualTo(13);
    }

    @DisplayName("에이스가 포함되었는지 알 수 있다.")
    @MethodSource
    @ParameterizedTest
    void hasAce(Cards cards, boolean expected) {
        assertThat(cards.hasAce()).isEqualTo(expected);
    }

    static Stream<Arguments> hasAce() {
        return Stream.of(
                Arguments.of(new Cards(), false),
                Arguments.of(new Cards(List.of(new Card(CardShape.SPADE, CardNumber.THREE))), false),
                Arguments.of(new Cards(List.of(new Card(CardShape.SPADE, CardNumber.ACE))), true)
        );
    }

}
