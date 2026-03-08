package domain.player;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    void 중복된_닉네임은_예외처리() {
        Name name = Name.from("나무");
        Player player1 = new Player(name);
        Player player2 = new Player(name);

        assertThatThrownBy(() -> Players.of(List.of(player1, player2)))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
