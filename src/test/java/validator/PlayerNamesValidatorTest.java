package validator;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import model.participant.exception.DuplicatePlayerNameException;
import org.junit.jupiter.api.Test;

public class PlayerNamesValidatorTest {
    @Test
    void 플레이어들의_이름이_중복되지_않으면_통과한다() {
        // given
        List<String> uniqueNames = List.of("pobi", "jason", "brown");

        // when and then
        assertThatNoException().isThrownBy(() -> PlayerNamesValidator.validate(uniqueNames));
    }

    @Test
    void 플레이어들의_이름에_중복이_존재하면_예외를_발생한다() {
        // given
        List<String> duplicatedNames = List.of("pobi", "jason", "pobi");

        // when and then
        assertThatThrownBy(() -> PlayerNamesValidator.validate(duplicatedNames))
                .isInstanceOf(DuplicatePlayerNameException.class);
    }
}
