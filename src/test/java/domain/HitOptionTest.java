package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class HitOptionTest {

    @DisplayName("y 혹은 n 이 아니면 예외를 발생한다.")
    @Test
    void createHitOptionWithInvalidInputTest() {
        assertThatThrownBy(() -> new HitOption("A"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] y 혹은 n 만 입력 가능합니다.");
    }

    @DisplayName("y 혹은 n 이면 메서드 호출에 성공한다")
    @ParameterizedTest
    @ValueSource(strings = {"y", "Y", "n", "N"})
    void createHitOptionWithValidInputTest(String value) {
        assertThatCode(() -> new HitOption(value)).doesNotThrowAnyException();
    }

    @DisplayName("y면 카드를 더 받는다(Hit).")
    @Test
    void meanHitAccordingToLetter() {
        HitOption hitOption = new HitOption("y");
        assertThat(hitOption.doHit()).isTrue();
    }

    @DisplayName("n이면 카드를 더 받지 않는다.")
    @Test
    void meanNoHitAccordingToLetter() {
        HitOption hitOption = new HitOption("n");
        assertThat(hitOption.doHit()).isFalse();
    }
}
