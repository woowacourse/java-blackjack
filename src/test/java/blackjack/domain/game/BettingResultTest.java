package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.user.Player;
import blackjack.domain.vo.Money;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingResultTest {

    @Test
    @DisplayName("딜러의 수익을 계산한다")
    void dealerProfit() {
        BettingResult bettingResult = new BettingResult(
                Map.of(
                        new Player("박스터"), new Money(-10000),
                        new Player("코즈"), new Money(10000),
                        new Player("제이미"), new Money(20000)
                )
        );

        assertThat(bettingResult.getDealerBettingResult()).isEqualTo(new Money(-20000));
    }

}
