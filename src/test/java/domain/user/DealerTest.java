package domain.user;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = Dealer.appoint();
    }

    @Test
    @DisplayName("생성 확인")
    void create() {
        assertThat(Dealer.appoint()).isNotNull();
    }

    @ParameterizedTest
    @DisplayName("딜러 기준 드로우 가능한지 확인")
    @MethodSource("createOption")
    void isAvailableToDraw(Card card, boolean expected) {
        dealer.draw(new Card(Symbol.CLOVER, Type.TEN));

        dealer.cards.add(card);

        assertThat(dealer.isAvailableToDraw()).isEqualTo(expected);
    }

    private static Stream<Arguments> createOption() {
        return Stream.of(
                Arguments.of(new Card(Symbol.DIAMOND, Type.SIX), true),
                Arguments.of(new Card(Symbol.DIAMOND, Type.SEVEN), false),
                Arguments.of(new Card(Symbol.DIAMOND, Type.ACE), false)
        );
    }
}