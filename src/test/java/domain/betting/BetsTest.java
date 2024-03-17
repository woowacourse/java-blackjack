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
        Money money1 = new Money(2000);
        Money money2 = new Money(5000);

        assertThatCode(() -> new Bets(Map.of(playerName1, money1, playerName2, money2)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("player을 통해 해당 플레이어의 베팅액을 반환 한다.")
    void get() {
        PlayerName playerName1 = new PlayerName("wiib");
        PlayerName playerName2 = new PlayerName("Bewhy");
        Money money1 = new Money(2000);
        Money money2 = new Money(5000);

        Bets playerBets = new Bets(Map.of(playerName1, money1, playerName2, money2));

        assertThat(playerBets.get(playerName1)).isEqualTo(money1);
        assertThat(playerBets.get(playerName2)).isEqualTo(money2);
    }

}

