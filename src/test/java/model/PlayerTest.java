package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Test
    @DisplayName("이름을 불러오는 지 테스트")
    void name() {
        Player player = new Player("DD");
        assertThat(player.getName()).isEqualTo("DD");
    }
}