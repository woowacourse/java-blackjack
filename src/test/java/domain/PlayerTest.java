package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("드로우 테스트")
    void isAbleDrawCard() {
        Player player = new Player("jamie");
        player.drawCard();
        Assertions.assertThat(player.isAbleDrawCards())
                .isTrue();
        for (int i = 0; i < 12; i++) {
            player.drawCard();
        }
        Assertions.assertThat(player.isAbleDrawCards())
                .isFalse();
    }
}
