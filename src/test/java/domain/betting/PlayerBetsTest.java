package domain.betting;

import static domain.FixtureCard.TEN_HEARTS;
import static domain.FixtureCard.TWO_HEARTS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import domain.card.Hand;
import domain.player.Name;
import domain.player.Player;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerBetsTest {

    @Test
    @DisplayName("플레이어와 해당 플레이어의 베팅액 쌍으로 초기화 한다.")
    void create() {
        Player player1 = new Player(new Name("wiib"), new Hand(List.of(TWO_HEARTS, TEN_HEARTS)));
        Player player2 = new Player(new Name("Bewhy"), new Hand(List.of(TWO_HEARTS, TEN_HEARTS)));
        Bet bet1 = new Bet(2000);
        Bet bet2 = new Bet(5000);

        assertThatCode(() -> new PlayerBets(Map.of(player1, bet1, player2, bet2)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("player을 통해 해당 플레이어의 베팅액을 반환 한다.")
    void get() {
        Player player1 = new Player(new Name("wiib"), new Hand(List.of(TWO_HEARTS, TEN_HEARTS)));
        Player player2 = new Player(new Name("Bewhy"), new Hand(List.of(TWO_HEARTS, TEN_HEARTS)));
        Bet bet1 = new Bet(2000);
        Bet bet2 = new Bet(5000);

        PlayerBets playerBets = new PlayerBets(Map.of(player1, bet1, player2, bet2));

        assertThat(playerBets.get(player1)).isEqualTo(bet1);
    }

}

