package blackjack.domain.bet;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitTest {

    @Test
    @DisplayName("숫자를 입력하면 수익 객체가 생성된다.")
    void create() {
        int input = 10000;

        assertThat(new Profit(input)).isInstanceOf(Profit.class);
    }

    @Test
    @DisplayName("get은 수익 객체가 가진 값을 반환한다.")
    void get() {
        int input = 10000;
        Profit profit = new Profit(input);

        assertThat(profit.get()).isEqualTo(input);
    }

    @Test
    @DisplayName("equals, hashCode, toString 테스트")
    void equals() {
        int input = 10000;
        Profit o1 = new Profit(input);
        Profit o2 = new Profit(input);
        Object o = new Object();

        assertThat(o1.equals(o2)).isTrue();
        assertThat(o1.equals(o1)).isTrue();
        assertThat(o1.equals(o)).isFalse();
        assertThat(o1.hashCode() == o2.hashCode()).isTrue();
        assertThat(o1.toString()).isEqualTo(o2.toString());
    }
}