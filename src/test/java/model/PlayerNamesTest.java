package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import exception.GameException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PlayerNamesTest {

    @Test
    public void 플레이어_이름_저장_정상_동작() {
        PlayerName playerName = new PlayerName("player1");
        PlayerName playerName2 = new PlayerName("player2");

        PlayerNames playerNames = new PlayerNames(List.of(playerName, playerName2));

        List<PlayerName> names = playerNames.getPlayerNames();

        assertThat(names.size()).isEqualTo(2);
        assertThat(names.getFirst()).isEqualTo(playerName);
        assertThat(names.getLast()).isEqualTo(playerName2);
    }

    @Test
    public void 중복_이름_예외() {
        PlayerName playerName = new PlayerName("player");
        PlayerName playerName2 = new PlayerName("player");

        assertThatThrownBy(() -> new PlayerNames(List.of(playerName, playerName2)))
                .isExactlyInstanceOf(GameException.class)
                .hasMessage("중복된 이름이 있습니다.");
    }
}

