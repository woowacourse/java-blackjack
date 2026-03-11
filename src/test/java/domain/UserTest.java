package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import vo.GameResult;
import vo.Money;

public class UserTest {
    @ParameterizedTest
    @DisplayName("게임 결과에 따라 수익을 계산한다.")
    @CsvSource(value = {
            "WIN, 10000",
            "LOSE, -10000",
            "BLACKJACK, 15000",
            "LOSE_BY_BLACKJACK, -15000",
            "DRAW, 0"
    })
    void 수익_계산_테스트(GameResult gameResult, int expectedAmount) {
        // given
        User user = new User("라이", new Money(10000));

        // when
        Money profit = user.updateProfitBy(gameResult);

        // then
        assertThat(profit).isEqualTo(new Money(expectedAmount));
    }
}
