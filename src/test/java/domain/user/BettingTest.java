package domain.user;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingTest {

    @DisplayName("배팅을 할 수 있다.")
    @Test
    void
    test1() {

        //given
        long amount = 30000000;

        //when
        Betting betting = new Betting(amount);

        //then
        Assertions.assertThat(betting.getBettingMoney()).isEqualTo(30000000);
    }

    @DisplayName("배팅금액이 음수 혹은 0일시 예외가 발생한다.")
    @Test
    void test2() {

        //given
        long minusAmount = -100000;

        // when & then
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThatIllegalArgumentException().isThrownBy(() -> new Betting(minusAmount))
                    .withMessage("배팅금액을 1원 이상 입력해 주세요.");
        });
    }

    @DisplayName("배팅금액이 음수 혹은 0일시 예외가 발생한다.")
    @Test
    void test3() {

        //given
        long zeroAmount = 0;

        // when & then
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThatIllegalArgumentException().isThrownBy(() -> new Betting(zeroAmount))
                    .withMessage("배팅금액을 1원 이상 입력해 주세요.");
        });
    }
}
