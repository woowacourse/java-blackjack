package domain.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

@SuppressWarnings("NonAsciiCharacters")
public class PlayerTest {

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
}
