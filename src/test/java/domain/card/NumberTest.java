package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NumberTest {


    @Test
    @DisplayName("ACE 카드는 합이 11 이하일 때 숫자가 11로 사용된다.")
    void sumCardContainingAceTest() {
        int sum = 3;

        assertThat(Number.sumContainingSoftAce(sum)).isEqualTo(sum + 10);
    }

    @Test
    @DisplayName("ACE 카드는 합이 11 초과일 때 숫자가 1로 사용된다.")
    void sumCardContainingAceTest2() {
        int sum = 13;

        assertThat(Number.sumContainingSoftAce(sum)).isEqualTo(sum);
    }
}
