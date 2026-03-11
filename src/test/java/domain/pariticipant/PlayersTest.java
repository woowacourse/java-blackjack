package domain.pariticipant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static exception.ErrorMessage.PLAYER_COUNT_OUT_OF_RANGE;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static test_util.TestUtil.createPlayer;

class PlayersTest {
    
    @ParameterizedTest
    @ValueSource(ints = {1, 7})
    @DisplayName("플레이어는 최소 1명 최대 7명이어야 한다.")
    public void players_success(int playerNum) throws Exception {
        // given
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < playerNum; i++) {
            players.add(createPlayer("name" + i, new ArrayList<>()));
        }
        
        // when then
        assertThatCode(() -> new Players(players))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 8})
    @DisplayName("플레이어는 1명 미만이거나 7명 초과면 예외가 발생한다..")
    public void players_fail(int playerNum) throws Exception {
        // given
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < playerNum; i++) {
            players.add(createPlayer("name" + i, new ArrayList<>()));
        }

        // when then
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PLAYER_COUNT_OUT_OF_RANGE.getMessage());
    }

}
