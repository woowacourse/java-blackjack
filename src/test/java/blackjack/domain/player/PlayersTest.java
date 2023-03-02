package blackjack.domain.player;

import static org.junit.jupiter.api.Assertions.*;

import blackjack.domain.player.exception.DuplicatedPlayerNameException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("플레이어의 이름이 중복되면 예외가 발생한다")
    void checking_player_name_duplicated() {
        assertThrows(DuplicatedPlayerNameException.class,
                () -> Players.from(List.of("pobi", "pobi")));
    }
}
