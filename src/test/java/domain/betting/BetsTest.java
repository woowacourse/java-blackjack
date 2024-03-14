package domain.betting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import domain.player.PlayerName;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BetsTest {

    @Test
    @DisplayName("플레이어와 해당 플레이어의 베팅액 쌍으로 초기화 한다.")
    void create() {
        PlayerName playerName1 = new PlayerName("wiib");
        PlayerName playerName2 = new PlayerName("Bewhy");
        Bet bet1 = new Bet(2000);
        Bet bet2 = new Bet(5000);

        assertThatCode(() -> new Bets(Map.of(playerName1, bet1, playerName2, bet2)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("player을 통해 해당 플레이어의 베팅액을 반환 한다.")
    void get() {
        PlayerName playerName1 = new PlayerName("wiib");
        PlayerName playerName2 = new PlayerName("Bewhy");
        Bet bet1 = new Bet(2000);
        Bet bet2 = new Bet(5000);

        Bets playerBets = new Bets(Map.of(playerName1, bet1, playerName2, bet2));

        assertThat(playerBets.get(playerName1)).isEqualTo(bet1);
        assertThat(playerBets.get(playerName2)).isEqualTo(bet2);
    }

}

