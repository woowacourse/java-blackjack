package blackjack.model.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class NamesTest {

    @Test
    void 이름이_중복되면_예외를_던진다() {
        // given
        List<Name> duplicatedNames = List.of(
                new Name("pobi"),
                new Name("pobi")
        );

        // when
        assertThatThrownBy(() -> new Names(duplicatedNames))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
