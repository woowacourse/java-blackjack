package blackjack.domain.bet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.player.PlayerName;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("베팅 정보들 도메인 테스트")
class BetsTest {

    @DisplayName("플레이어 이름으로 등록된 베팅정보를 찾아올 수 있다")
    @Test
    void testGetBetByPlayerName() {
        Bet bet1 = new Bet(new PlayerName("리비"), new Money(1_000));
        Bet bet2 = new Bet(new PlayerName("썬"), new Money(1_000_000));
        Bets bets = new Bets(List.of(bet1, bet2));

        Bet found = bets.findBetByPlayerName("리비");
        assertThat(found.getBetAmount().getAmount()).isEqualTo(1_000);
    }

    @DisplayName("플레이어 이름으로 등록된 베팅정보를 찾을 수 없을 경우 예외를 발생시킨다")
    @Test
    void testFindBetWithInvalidPlayerName() {
        Bet bet1 = new Bet(new PlayerName("리비"), new Money(1_000));
        Bet bet2 = new Bet(new PlayerName("썬"), new Money(1_000_000));
        Bets bets = new Bets(List.of(bet1, bet2));

        assertThatThrownBy(() -> bets.findBetByPlayerName("제리"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[INTERNAL_ERROR] 해당 이름으로 등록된 베팅 정보를 찾을 수 없습니다");
    }
}
