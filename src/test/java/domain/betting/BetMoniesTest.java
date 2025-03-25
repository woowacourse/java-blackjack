package domain.betting;

import static org.assertj.core.api.Assertions.assertThat;

import domain.fixture.PlayerBetMoneyFixture;
import domain.fixture.PlayerNameFixture;
import domain.participant.PlayerName;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetMoniesTest {

    @Test
    @DisplayName("참여자의 이름을 토대로 배팅 정보를 가져온다.")
    void should_return_betMoney_by_player_name() {
        // given
        PlayerName playerName = PlayerNameFixture.playerNameFirst;
        PlayerBetMoney playerBetMoney = PlayerBetMoneyFixture.FIRST_10000;
        BetMonies betMonies = new BetMonies(List.of(playerBetMoney));

        // when
        PlayerBetMoney foundPlayerBetMoney = betMonies.findByPlayerName(playerName);

        // then
        assertThat(foundPlayerBetMoney).isEqualTo(playerBetMoney);
    }
}
