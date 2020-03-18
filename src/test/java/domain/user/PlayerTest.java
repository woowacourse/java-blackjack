package domain.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class PlayerTest {

    @Test
    void 플레이어_생성_테스트() {
        Player player = new Player("KIM");

        Assertions.assertThat(player).hasFieldOrPropertyWithValue("name", "KIM");
    }
}
