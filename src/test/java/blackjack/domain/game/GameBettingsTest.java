package blackjack.domain.game;

import blackjack.domain.gameresult.Betting;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameBettingsTest {

    @DisplayName("성공 : 플레이어를 조회하면 플레이어의 배팅액을 반환한다")
    @Test
    void should_returnPlayerBet_When_GiveExistPlayer() {
        Map<Player, Betting> playerBattings = new LinkedHashMap<>();
        Player testPlayer = new Player("coli");
        playerBattings.put(testPlayer, Betting.from(100.0));
        GameBettings gameBettings = new GameBettings(playerBattings);

        Betting findBat = gameBettings.findPlayerBatting(testPlayer);

        assertThat(findBat).isEqualTo(Betting.from(100.0));
    }

    @DisplayName("실패 : 존재하지 않는 플레이어의 베팅액을 조회할 수 없다")
    @Test
    void should_ThrowIllegaStateException_When_GiveNonExistPlayer() {
        Map<Player, Betting> playerBattings = new LinkedHashMap<>();
        Player testPlayer = new Player("coli");
        playerBattings.put(testPlayer, Betting.from(100.0));
        GameBettings gameBettings = new GameBettings(playerBattings);

        assertThatThrownBy(() -> gameBettings.findPlayerBatting(new Player("pobi")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("조회한 플레이어의 베팅액이 없습니다.");
    }
}
