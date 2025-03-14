package domain.batting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import domain.participant.Player;
import org.junit.jupiter.api.Test;

class BettingPoolTest {

    @Test
    void 배팅_풀을_생성한다() {
        // given
        // nothing

        // when & then
        assertThatCode(() -> BettingPool.of())
                .doesNotThrowAnyException();
    }

    @Test
    void 사용자에_대한_배팅을_추가한다() {
        // given
        Player player = Player.of("pobi");
        Bet bet = Bet.of(10000);
        BettingPool bettingPool = BettingPool.of();

        // when
        boolean betted = bettingPool.wager(player, bet);
        boolean notBetted = bettingPool.wager(player, bet);

        // then
        assertThat(betted).isTrue();
        assertThat(notBetted).isFalse();
    }
}
