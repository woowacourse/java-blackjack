package domain.user;

import domain.card.CardDeckGeneratorForTest;
import domain.card.CardFactory;
import domain.card.Symbol;
import domain.card.Type;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

@SuppressWarnings("NonAsciiCharacters")
public class PlayerTest {
    private Player createPlayer() {
        Player player = new Player("KIM");

        Type type = Type.valueOf("SPADE");
        Symbol symbol = Symbol.valueOf("ACE");
        player.draw(CardDeckGeneratorForTest.createByOneCard(CardFactory.of(type, symbol)));
        type = Type.valueOf("HEART");
        symbol = Symbol.valueOf("SEVEN");
        player.draw(CardDeckGeneratorForTest.createByOneCard(CardFactory.of(type, symbol)));
        type = Type.valueOf("CLUB");
        symbol = Symbol.valueOf("KING");
        player.draw(CardDeckGeneratorForTest.createByOneCard(CardFactory.of(type, symbol)));

        return player;
    }

    @Test
    void 플레이어_생성_테스트() {
        Player player = new Player("KIM");

        Assertions.assertThat(player).hasFieldOrPropertyWithValue("name", "KIM");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 생성_예외처리_테스트(String input) {
        Assertions.assertThatThrownBy(() -> new Player(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 입력입니다.");
    }

    @Test
    void 뽑은_카드_결과_반환_테스트() {
        Player player = createPlayer();
        Assertions.assertThat(player.getStatus()).isEqualTo("KIM: A스페이드,7하트,K클로버");
    }

    @Test
    void 점수_반환_테스트() {
        Player player = createPlayer();
        Assertions.assertThat(player.getScore()).isEqualTo(18);
    }
}
