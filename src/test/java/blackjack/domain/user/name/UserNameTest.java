package blackjack.domain.user.name;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UserNameTest {

    @Test
    void 이름은_앞_뒤_공백을_제거한다() {
        // given
        UserName userName = new UserName(" 이름이에요 ");

        // then
        Assertions.assertThat(userName.getName()).isEqualTo("이름이에요");
    }
}
