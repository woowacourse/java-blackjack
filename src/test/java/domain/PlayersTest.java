package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("플레이어의 이름들로 플레이어를 모두 생성한다")
    @Test
    void test1() {
        //given
        List<String> playerNames = List.of("mimi", "wade", "pobi");

        //when
        Players players = Players.createByNames(playerNames);

        //then
        Assertions.assertThat(players.getPlayers()).containsExactlyInAnyOrderElementsOf(
                List.of(
                new Player("mimi", Cards.createEmpty()),
                new Player("wade", Cards.createEmpty()),
                new Player("pobi", Cards.createEmpty())
                )
        );
    }
}
