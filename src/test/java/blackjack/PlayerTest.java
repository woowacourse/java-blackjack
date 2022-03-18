package blackjack;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @DisplayName("이름 없이 생성 안되는지 확인")
    @Test
    void generateTest() {
        assertThatThrownBy(() -> Player.generate("")).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름 공백 생성 안되는지 확인하는 테스트")
    @Test
    void generateTest2() {
        assertThatThrownBy(() -> Player.generate(" ")).isInstanceOf(IllegalArgumentException.class);
    }
}
