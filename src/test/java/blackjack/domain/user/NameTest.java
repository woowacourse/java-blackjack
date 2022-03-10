package blackjack.domain.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {

    @Test
    @DisplayName("이름을 생성한다.")
    public void testNameCreation() {
        // given
        String input = "pobi";
        // when
        Name name = new Name(input);
        // then
        Assertions.assertThat(name).isNotNull();
    }
}