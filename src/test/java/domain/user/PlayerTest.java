package domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.result.WinningResult;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("이름");
    }

    @Test
    @DisplayName("생성 확인")
    void create() {
        assertThatCode(() -> new Player("이름"))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("플레이어가 드로우 가능한지 확인")
    @MethodSource("createOption")
    void isAvailableToDraw(Card card, boolean expected) {
        player.draw(new Card(Symbol.CLOVER, Type.TEN));
        player.draw(new Card(Symbol.CLOVER, Type.EIGHT));
        player.draw(card);

        assertThat(player.isAvailableToDraw()).isEqualTo(expected);
    }

    private static Stream<Arguments> createOption() {
        return Stream.of(
                Arguments.of(new Card(Symbol.DIAMOND, Type.TWO), true),
                Arguments.of(new Card(Symbol.DIAMOND, Type.THREE), false),
                Arguments.of(new Card(Symbol.DIAMOND, Type.FIVE), false)
        );
    }
}