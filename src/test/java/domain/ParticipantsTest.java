package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


class ParticipantsTest {
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
}
