package domain.card;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardTest {

    @Test
    @DisplayName("생성 확인")
    void create() {
        assertThat(new Card(Symbol.SPADE, Type.EIGHT)).isNotNull();
    }

    @ParameterizedTest
    @DisplayName("카드 타입이 ACE인지 확인")
    @MethodSource("createCard")
    void isAce(Card card, boolean expected) {
        assertThat(card.isAce()).isEqualTo(expected);
    }

    private static Stream<Arguments> createCard() {
        return Stream.of(
                Arguments.of(new Card(Symbol.DIAMOND, Type.ACE), true),
                Arguments.of(new Card(Symbol.DIAMOND, Type.FIVE), false)
        );
    }

    @Test
    void getPoint() {
        Card card = new Card(Symbol.SPADE, Type.FIVE);
        assertThat(card.getPoint()).isEqualTo(5);
    }
}