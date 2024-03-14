package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class BettingPotTest {

    @DisplayName("사용자와 배팅 기록을 기록한다.")
    @Test
    void create() {
        BettingPot bettingPot = new BettingPot();
        Player player = new Player("산초");
        Bet bet = new Bet(100);

        assertThatCode(() -> bettingPot.collect(player, bet))
                .doesNotThrowAnyException();
    }
}
