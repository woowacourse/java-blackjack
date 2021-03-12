package blackjack.domain.names;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NamesTest {

    @Test
    @DisplayName("이름들 입력 기능")
    void generateNames() {
        assertThat(Names.valueOf("pobi,jason,root").unwrap())
            .containsExactly(new Name("pobi"), new Name("jason"), new Name("root"));
    }

    @Test
    @DisplayName("중복된 이름이 있는 경우 예외 처리")
    void duplicateNames() {
        assertThatIllegalArgumentException().isThrownBy(() ->
            Names.valueOf("pobi,jason, pobi"))
            .withMessage("중복된 이름은 사용할 수 없습니다.");
    }
}
