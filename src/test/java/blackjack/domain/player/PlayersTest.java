package blackjack.domain.player;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.Fixtures;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {

    @Test
    @DisplayName("다음 플레이어가 있는지 확인")
    void checkIsExistNextPlayer() {
        List<Player> playerList = new ArrayList<>();
        playerList.add(Fixtures.guest);
        Players players = new Players(playerList);

        assertThat(players.hasNextTurn()).isTrue();
    }

    @Test
    @DisplayName("턴이 진행됨에 따라 올바른 플레이어가 나오는지 확인")
    void checkGetNextPlayer() {
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Dealer());
        Guest guest = Fixtures.guest;
        playerList.add(guest);

        Players players = new Players(playerList);
        players.nextTurn();

        assertThat(players.turnPlayer()).isEqualTo(guest);
    }
}
