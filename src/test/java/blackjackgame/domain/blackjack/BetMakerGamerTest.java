package blackjackgame.domain.blackjack;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BetMakerGamerTest {

    @Test
    @DisplayName("이름과 배팅금액을 갖는 게이머 클래스의 생성자를 만들 수 있다.")
    void createGamerWithNameAndBetMoneyConstructorTest() {
        Assertions.assertThatCode(() ->
                new BetMakerGamer("게이머", new BetMoney(0))
        ).doesNotThrowAnyException();
    }
}
