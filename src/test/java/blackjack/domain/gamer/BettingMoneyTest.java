package blackjack.domain.gamer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BettingMoneyTest {

    @Test
    @DisplayName("bettingMoney는 정수를 입력받아 생성")
    void bettingMoneyContructor() {
        BettingMoney bettingMoney = BettingMoney.from(1000);
        assertThat(bettingMoney.getBettingMoney()).isEqualTo(1000);
    }

    @Test
    @DisplayName("bettingMoney는 0이하의 값을 가질 수 없음")
    void bettingMoneyException() {
        assertThatThrownBy(() -> BettingMoney.from(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("betting money factory method 테스트")
    void bettingMoneyFactoryMethod() {
        assertThat(BettingMoney.from("1000").getBettingMoney()).isEqualTo(1000);
    }
}