package domain;

import domain.participant.Players;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


class PlayersTest {

    @Test
    void 플레이어의_이름이_중복되는_경우_예외를_발생시킨다() {
        List<String> names = List.of("pobi", "pobi");

        assertThatThrownBy(() -> new Players(names))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 플레이어의_수가_5인을_초과한_경우_예외를_발생시킨다() {
        List<String> names = List.of("pobi", "jason", "neo", "brown", "woni", "lisa");

        assertThatThrownBy(() -> new Players(names))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
