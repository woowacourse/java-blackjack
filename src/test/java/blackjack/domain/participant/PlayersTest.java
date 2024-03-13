package blackjack.domain.participant;

import blackjack.domain.gameresult.Batting;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersTest {

    @DisplayName("게임의 플레이어는 최소 1명이상이 참여해야 합니다")
    @Test
    void should_ThrowIllegalArgumentException_When_Lower_Than_Minimum_PlayerNumber() {
        List<Player> emptyPlayers = new ArrayList<>();
        assertThatThrownBy(() -> new Players(emptyPlayers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임 참여자는 최소 1명에서 최대 8명까지 가능합니다");
    }

    @DisplayName("게임의 플레이어는 최대 8명까지 참여할 수 있습니다")
    @Test
    void should_ThrowIllegalArgumentException_When_More_Than_Maximum_PlayerNumber() {
        List<Player> exceedPlayers = new ArrayList<>();
        exceedPlayers.add(new Player(new Name("pobi1"), Batting.from(1.0)));
        exceedPlayers.add(new Player(new Name("pobi2"), Batting.from(2.0)));
        exceedPlayers.add(new Player(new Name("pobi3"), Batting.from(3.0)));
        exceedPlayers.add(new Player(new Name("pobi4"), Batting.from(4.0)));
        exceedPlayers.add(new Player(new Name("pobi5"), Batting.from(5.0)));
        exceedPlayers.add(new Player(new Name("pobi6"), Batting.from(6.0)));
        exceedPlayers.add(new Player(new Name("pobi7"), Batting.from(7.0)));
        exceedPlayers.add(new Player(new Name("pobi8"), Batting.from(8.0)));
        exceedPlayers.add(new Player(new Name("pobi9"), Batting.from(9.0)));

        assertThatThrownBy(() -> new Players(exceedPlayers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임 참여자는 최소 1명에서 최대 8명까지 가능합니다");
    }
}
