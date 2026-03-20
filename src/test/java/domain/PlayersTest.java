package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.participant.Players;
import java.util.List;
import org.junit.jupiter.api.Test;

class PlayersTest {
    @Test
    void 참가자_이름이_중복인_경우_예외를_발생한다() {
        List<String> names = List.of("pobi", "pobi");

        assertThatThrownBy(() -> new Players(names))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 참가자가_5인을_초과한_경우_예외를_발생한다() {
        List<String> names = List.of("pobi", "jason", "neo", "brown", "woni", "lisa");

        assertThatThrownBy(() -> new Players(names))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 참가자들의_이름을_반환한다() {
        Players players = new Players(List.of("pobi", "jason"));

        assertThat(players.getPlayerNames()).contains("pobi", "jason");
    }
}
