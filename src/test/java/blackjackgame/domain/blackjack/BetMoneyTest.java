package blackjackgame.domain.blackjack;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BetMoneyTest {

    @Test
    @DisplayName("배팅금액을 갖는 배팅금액 생성자를 만들 수 있다.")
    void createBetMoneyConstructorTest() {
        Assertions.assertThatCode(() ->
                new BetMoney(0)
        ).doesNotThrowAnyException();
    }
}
