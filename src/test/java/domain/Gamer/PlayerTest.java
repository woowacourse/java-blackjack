package domain.Gamer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    @DisplayName("플레이어가 카드를 추가로 받는 경우")
    void isDrawCardTest() {
        Assertions.assertThat(Player.isDrawable(YesOrNo.YES)).isTrue();
    }

    @Test
    @DisplayName("플레이어가 카드를 추가로 받는 경우")
    void isNotDrawCardTest() {
        Assertions.assertThat(Player.isDrawable(YesOrNo.NO)).isFalse();
    }
}
