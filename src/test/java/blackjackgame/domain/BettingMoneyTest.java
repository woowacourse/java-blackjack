package blackjackgame.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BettingMoneyTest {
    @DisplayName("입력된 베팅 금액이 1000 미만일 때 예외를 반환하는지 확인한다.")
    @Test
    void Shoud_ReturnException_When_BettingMoneyIsLowerThanThousand() {
        int inputMoney = 999;
        Assertions.assertThrows(IllegalArgumentException.class, () -> BettingMoney.of(inputMoney));
    }

    @DisplayName("입력된 베팅 금액이 1000이상일 때 제대로 생성되고 입력된 값을 가지는지 확인한다.")
    @Test
    void Shoud_ReturnInputMoney_When_InputMoneyIsOverThanThousand() {
        int inputMoney = 1000;
        BettingMoney bettingMoney = BettingMoney.of(inputMoney);
        assertThat(bettingMoney.money()).isEqualTo(inputMoney);
    }
}
