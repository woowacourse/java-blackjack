package domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.YesOrNo;
import domain.deck.Card;
import domain.deck.DeckFactory;
import domain.deck.Symbol;
import domain.deck.Type;

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

    @Test
    @DisplayName("첫 카드 분배 결과")
    void getFirstDrawResult() {
        player.draw(new Card(Symbol.CLOVER, Type.EIGHT));
        player.draw(new Card(Symbol.DIAMOND, Type.ACE));

        assertThat(player.getFirstDrawResult()).isEqualTo("이름카드: 8클로버, A다이아몬드");
    }


    @ParameterizedTest
    @DisplayName("딜러 기준 포인트에 따른 드로우")
    @MethodSource("createOption")
    void additionalDraw(YesOrNo input, int expected) {
        player.draw(new Card(Symbol.CLOVER, Type.TEN));
        int initSize = player.cards.size();

        player.additionalDraw(DeckFactory.getDeck(), input);

        assertThat(player.cards.size()).isEqualTo(initSize + expected);
    }

    private static Stream<Arguments> createOption() {
        return Stream.of(
                Arguments.of(YesOrNo.of("y"), 1),
                Arguments.of(YesOrNo.of("n"), 0)
        );
    }
}