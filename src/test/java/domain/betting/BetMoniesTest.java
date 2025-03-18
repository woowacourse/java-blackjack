package domain.betting;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetMoniesTest {

    @Test
    @DisplayName("참여자의 이름을 토대로 배팅 정보를 가져온다.")
    void should_return_betMoney_by_player_name() {
        // given
        String playerName = "a";
        int money = 10000;
        BetMoney betMoney = new BetMoney(playerName, money);
        BetMonies betMonies = new BetMonies(List.of(betMoney));

        // when
        BetMoney foundBetMoney = betMonies.findByPlayerName(playerName);

        // then
        assertThat(foundBetMoney).isEqualTo(betMoney);
    }
}
