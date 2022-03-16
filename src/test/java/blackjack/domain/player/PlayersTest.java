package blackjack.domain.player;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card.PlayingCards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {

    @Test
    @DisplayName("다음 플레이어가 있는지 확인")
    void checkNextPlayer() {
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Guest("guest", new PlayingCards()));
        Players players = new Players(playerList);

        assertThat(players.hasNextTurn()).isTrue();
    }

    @Test
    @DisplayName("턴이 진행됨에 따라 올바른 플레이어가 나오는지 확인")
    void checkGetNextPlayer() {
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Dealer());
        Guest guest = new Guest("guest", new PlayingCards());
        playerList.add(guest);

        Players players = new Players(playerList);
        players.nextTurn();

        assertThat(players.turnPlayer()).isEqualTo(guest);
    }
}
