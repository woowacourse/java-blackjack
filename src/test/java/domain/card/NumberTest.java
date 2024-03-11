package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NumberTest {
    @Test
    @DisplayName("합이 11 이하이면 합에 10을 더해준다.")
    void sumContainingSoftAceTest() {
        int sum = 3;

        assertThat(Number.sumContainingSoftAce(sum)).isEqualTo(sum + 10);
    }

    @Test
    @DisplayName("합이 11 초과이면 그대로 반환한다.")
    void sumCardNotContainingSoftAceTest() {
        int sum = 13;

        assertThat(Number.sumContainingSoftAce(sum)).isEqualTo(sum);
    }
}
