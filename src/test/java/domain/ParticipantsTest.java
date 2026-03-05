package domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


class ParticipantsTest {
    @Test
    void 참가자_이름이_중복인_경우_예외를_발생한다() {
        List<String> names = List.of("pobi", "pobi");

        assertThatThrownBy(() -> new Participants(names))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 참가자가_5인을_초과한_경우_예외를_발생한다() {
        List<String> names = List.of("pobi", "brown", "바니", "초록", "coffee", "x");

        assertThatThrownBy(() -> new Participants(names))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
