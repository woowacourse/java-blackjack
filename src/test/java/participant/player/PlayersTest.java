package participant.player;

import java.util.ArrayList;
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
        List<Player> players = List.of(new Player(new ArrayList<>(), new Name("pola"), new BetMoney(3000)));

        Assertions.assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참가자의 인원은 최소 " + MINIMUM_PLAYER_RANGE + "에서 최대 " + MAXIMUM_PLAYER_RANGE + "명 까지 가능합니다.");
    }

    @DisplayName("플레이어의 인원이 최소 8명보다 많을 경우 Error를 throw 한다.")
    @Test
    void isOverPossiblePlayerRange() {
        List<Player> players = List.of(new Player(new ArrayList<>(), new Name("pola"), new BetMoney(3000)),
                new Player(new ArrayList<>(), new Name("ato"), new BetMoney(3000)),
                new Player(new ArrayList<>(), new Name("hogi"), new BetMoney(3000)),
                new Player(new ArrayList<>(), new Name("jazz"), new BetMoney(3000)),
                new Player(new ArrayList<>(), new Name("cola"), new BetMoney(3000)),
                new Player(new ArrayList<>(), new Name("bumble"), new BetMoney(3000)),
                new Player(new ArrayList<>(), new Name("neo"), new BetMoney(3000)),
                new Player(new ArrayList<>(), new Name("sola"), new BetMoney(3000)),
                new Player(new ArrayList<>(), new Name("bri"), new BetMoney(3000))
        );

        Assertions.assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참가자의 인원은 최소 " + MINIMUM_PLAYER_RANGE + "에서 최대 " + MAXIMUM_PLAYER_RANGE + "명 까지 가능합니다.");
    }

    @DisplayName("플레이어의 이름이 중복이 되는 경우 에러를 반환한다.")
    @Test
    void isPlayersHasDuplicateName() {
        List<Player> players = List.of(new Player(new ArrayList<>(), new Name("pola"), new BetMoney(3000)),
                new Player(new ArrayList<>(), new Name("ato"), new BetMoney(3000)),
                new Player(new ArrayList<>(), new Name("pola"), new BetMoney(3000))
        );

        Assertions.assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참가자는 중복된 이름을 가질 수 없습니다.");
    }
}
