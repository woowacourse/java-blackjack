package domain.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CostTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 99, 10_000_001})
    void 배팅_금액_범위를_넘어가면_에러(int cost) {
        Assertions.assertThatThrownBy(() -> new Cost(cost))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]", "사이만");
    }

    @ParameterizedTest
    @ValueSource(ints = {101, 199, 222})
    void 배팅_금액이_설정된_단위가_아닐때(int cost) {
        Assertions.assertThatThrownBy(() -> new Cost(cost))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]", "최소 단위");
    }
}
