package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.participant.UserName;
import org.junit.jupiter.api.Test;

class UserNameTest {

    @Test
    void 유저_이름이_공백일_경우_예외_발생() {
        // given
        String name = " ";

        // when & then
        assertThatThrownBy(() -> new UserName(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("공백");
    }

    @Test
    void 유저_이름이_5자가_넘을경우_예외_발생() {
        // given
        String name = "흑곰흑곰흑곰";

        // when & then
        assertThatThrownBy(() -> new UserName(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("5자");
    }

}