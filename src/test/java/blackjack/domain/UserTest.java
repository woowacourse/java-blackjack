package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    @DisplayName("유저_생성_테스트")
    void 유저_생성_테스트() {
        // given
        String name = "흑곰";

        // when & then
        assertThatCode(() -> new User(name))
                .doesNotThrowAnyException();
    }

}
