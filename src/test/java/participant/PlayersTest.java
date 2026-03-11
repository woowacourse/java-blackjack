package participant;

import domain.participant.Hand;
import domain.participant.Player;
import domain.participant.PlayerName;
import domain.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersTest {
    private static final String PLAYER_NAME_DUPLICATED_ERROR_MESSAGE = "[ERROR] 동명이인은 존재하지 않습니다!";

    @Test
    @DisplayName("플레이어들의 이름이 중복되면 예외가 발생한다")
    void validateUniquePlayerName_whenPlayerNamesAreDuplicated() {
        List<Player> players =
                List.of(
                        new Player(new PlayerName("이삭"), new Hand()),
                        new Player(new PlayerName("이삭"), new Hand()),
                        new Player(new PlayerName("포비"), new Hand())
                );

        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PLAYER_NAME_DUPLICATED_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("플레이어들의 이름이 중복되지 않으면 올바르게 생성한다")
    void constructor_createsSuccessfully_whenPlayerNamesAreUnique() {
        List<Player> dummyplayers =
                List.of(
                        new Player(new PlayerName("이삭"), new Hand()),
                        new Player(new PlayerName("네오"), new Hand()),
                        new Player(new PlayerName("포비"), new Hand())
                );

        Players players = new Players(dummyplayers);

        assertThat(players.getAllPlayers()).isEqualTo(dummyplayers);
    }
}
