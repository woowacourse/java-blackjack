package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class NamesTest {

    @Test
    @DisplayName("이름이 중복일 경우 예외가 발생한다.")
    void validate_DuplicatedNames_ExceptionThrown() {
        Name tenny = new Name("tenny");
        Name tenny2 = new Name("tenny");
        List<Name> names = List.of(tenny, tenny2);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Names(names));
    }
}
