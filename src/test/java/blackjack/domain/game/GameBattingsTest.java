package blackjack.domain.game;

import blackjack.domain.gameresult.Batting;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameBattingsTest {

    @DisplayName("성공 : 플레이어를 조회하면 플레이어의 배팅액을 반환한다")
    @Test
    void should_returnPlayerBet_When_GiveExistPlayer() {
        Map<Player, Batting> playerBattings = new LinkedHashMap<>();
        Player testPlayer = new Player("coli");
        playerBattings.put(testPlayer, Batting.from(100.0));
        GameBattings gameBattings = new GameBattings(playerBattings);

        Batting findBat = gameBattings.findPlayerBatting(testPlayer);

        assertThat(findBat).isEqualTo(Batting.from(100.0));
    }

    @DisplayName("실패 : 존재하지 않는 플레이어의 베팅액을 조회할 수 없다")
    @Test
    void should_ThrowIllegaStateException_When_GiveNonExistPlayer() {
        Map<Player, Batting> playerBattings = new LinkedHashMap<>();
        Player testPlayer = new Player("coli");
        playerBattings.put(testPlayer, Batting.from(100.0));
        GameBattings gameBattings = new GameBattings(playerBattings);

        assertThatThrownBy(() -> gameBattings.findPlayerBatting(new Player("pobi")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("조회한 플레이어의 베팅액이 없습니다.");
    }
}
