package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.profit.PlayersProfit;
import blackjack.domain.profit.Profit;
import fixture.PlayerFixture;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("플레이어가 없으면 예외가 발생한다.")
    @Test
    void testCreatePlayersWithEmptyEntry() {
        assertThatThrownBy(() -> new Players(List.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복되는 플레이어의 이름이 존재하면 예외가 발생한다.")
    @Test
    void testCreatePlayersWithDuplicateNames() {
        Player player1 = new Player(new PlayerName("pobi"));
        Player player = new Player(new PlayerName("pobi"));

        assertThatThrownBy(() -> new Players(List.of(player1, player)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("플레이어들을 생성한다.")
    @Test
    void testCreateWithValidPlayers() {
        Player pobi = new Player(new PlayerName("pobi"));
        Player jason = new Player(new PlayerName("jason"));

        assertThatCode(() -> new Players(List.of(pobi, jason)))
                .doesNotThrowAnyException();
    }

    @DisplayName("주어진 플레이어 이름 리스트로 플레이어들을 생성한다.")
    @Test
    void testCreateByNameList() {
        // given
        List<String> playerNames = List.of("pobi", "jason");

        // when
        Players players = Players.create(playerNames);

        // then
        assertThat(players.getPlayers()).containsExactly(
                new Player(new PlayerName("pobi")),
                new Player(new PlayerName("jason"))
        );
    }

    @DisplayName("모든 플레이어가 배팅한다.")
    @Test
    void testBet() {
        // given
        Player pobi = PlayerFixture.createPobi();
        Player jason = PlayerFixture.createJason();
        Players players = new Players(List.of(pobi, jason));

        Function<Player, Integer> betByPlayer = player -> getBetAmount(player, pobi, jason);

        // when
        PlayersProfit profits = players.bet(betByPlayer);

        // then
        assertThat(profits.getProfits()).containsExactlyEntriesOf(Map.of(
                PlayerFixture.createPobi(), new Profit(10000),
                PlayerFixture.createJason(), new Profit(20000)
        ));
    }

    private int getBetAmount(Player player, Player pobi, Player jason) {
        if (player.equals(pobi)) {
            return 10000;
        }
        if (player.equals(jason)) {
            return 20000;
        }
        throw new IllegalArgumentException();
    }
}
