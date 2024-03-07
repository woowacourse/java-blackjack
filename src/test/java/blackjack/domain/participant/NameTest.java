package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    public static final Name DEFAULT_NAME = new Name("name");

    @DisplayName("이름은 적어도 한 글자를 가져야 한다.")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\n"})
    void validateTest_whenNameIsEmpty_throwException(String name) {

        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 적어도 한 글자 이상을 포함해야 합니다.");
    }

    @DisplayName("이름의 앞 뒤 공백을 제거해준다.")
    @ParameterizedTest
    @ValueSource(strings = {" pobi", "pobi ", " pobi ", "\npobi\t"})
    void validateTest_nameHasStrippedName(String input) {
        Name name = new Name(input);

        assertThat(name.getName()).isEqualTo("pobi");
    }
}
