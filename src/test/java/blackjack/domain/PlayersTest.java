package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    @DisplayName("Players 클래스는 Player들을 입력받으면 정상적으로 생성된다.")
    void create_players() {
        Player aki = new Player(new Name("aki"), new ArrayList<>());
        Player alien = new Player(new Name("alien"), new ArrayList<>());
        List<Player> players = new ArrayList<>();
        players.add(aki);
        players.add(alien);

        assertThatCode(() -> new Players(players)).doesNotThrowAnyException();
    }
}
