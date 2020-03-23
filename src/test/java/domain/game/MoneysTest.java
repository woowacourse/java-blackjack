package domain.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneysTest {
    @Test
    void 추가_테스트() {
        Moneys moneys = new Moneys();
        moneys.add("kim", new Money(1000));

        Assertions.assertThat(moneys.getValue("kim").getValue()).isEqualTo(1000);
    }

    @Test
    void 추가_null_예외처리_테스트() {
        Moneys moneys = new Moneys();

        Assertions.assertThatThrownBy(() -> moneys.add(null, new Money(1000)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 값이 전달되었습니다.");
        Assertions.assertThatThrownBy(() -> moneys.add("kim", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 값이 전달되었습니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {0,-1,-10000})
    void 추가_금액_값_예외_테스트(int value) {
        Moneys moneys = new Moneys();

        Assertions.assertThatThrownBy(() -> moneys.add(null, new Money(value)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 값이 전달되었습니다.");
    }
}
