package player;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    private static final int MINIMUM_PLAYER_RANGE = 2;
    private static final int MAXIMUM_PLAYER_RANGE = 8;

    @DisplayName("플레이어의 인원이 최소 2명보다 적을 경우 Error를 throw 한다.")
    @Test
    void isNotOverPossiblePlayerRange() {
        List<Player> players = List.of(
                new Player(new Name("pola"))
        );

        Assertions.assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참가자의 인원은 최소 " + MINIMUM_PLAYER_RANGE + "에서 최대 " + MAXIMUM_PLAYER_RANGE + "명 까지 가능합니다.");
    }

    @DisplayName("플레이어의 인원이 최소 8명보다 많을 경우 Error를 throw 한다.")
    @Test
    void isOverPossiblePlayerRange() {
        List<Player> players = List.of(
                new Player(new Name("pola")),
                new Player(new Name("ato")),
                new Player(new Name("kaki")),
                new Player(new Name("hogi")),
                new Player(new Name("jazz")),
                new Player(new Name("pobi")),
                new Player(new Name("lisa")),
                new Player(new Name("takan")),
                new Player(new Name("siso"))
        );

        Assertions.assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참가자의 인원은 최소 " + MINIMUM_PLAYER_RANGE + "에서 최대 " + MAXIMUM_PLAYER_RANGE + "명 까지 가능합니다.");
    }
}
