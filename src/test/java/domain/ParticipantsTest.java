package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


class ParticipantsTest {
    @Test
    void 참가자_이름이_중복인_경우_예외를_발생한다() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("pobi"));
        players.add(new Player("pobi"));

        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 참가자가_5인을_초과한_경우_예외를_발생한다() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("jason"));
        players.add(new Player("pobi"));
        players.add(new Player("neo"));
        players.add(new Player("brown"));
        players.add(new Player("lisa"));
        players.add(new Player("woni"));

        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
