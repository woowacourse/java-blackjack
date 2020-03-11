package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TypeTest {
    @Test
    @DisplayName("타입이 생성된다.")
    void valueOf() {
        assertThat(Type.valueOf("HEART")).isEqualTo(Type.HEART);
    }

    @Test
    @DisplayName("패턴이 올바르게 구해진다")
    void getPattern() {
        assertThat(Type.HEART.getPattern()).isEqualTo("♡");
    }
}
