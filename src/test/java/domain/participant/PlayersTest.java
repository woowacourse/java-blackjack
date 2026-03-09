package domain.participant;

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

    @Test
    void 플레이어_이름이_7개_초과시_예외처리() {
        List<Player> players = List.of(
                        new Player(Name.from("나무1")),
                        new Player(Name.from("나무2")),
                        new Player(Name.from("나무3")),
                        new Player(Name.from("나무4")),
                        new Player(Name.from("나무5")),
                        new Player(Name.from("나무6")),
                        new Player(Name.from("나무7")),
                        new Player(Name.from("나무8"))
                );

        assertThatThrownBy(() -> Players.of(players))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
