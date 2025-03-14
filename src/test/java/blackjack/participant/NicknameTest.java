package blackjack.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class NicknameTest {

    @Test
    @DisplayName("닉네임은 공백이나 null이 될 수 없다")
    void nicknameShouldNotBlankOrNull() {
        // given
        String nullNickname = null;
        String blankNickname = "";

        // when
        // then
        assertAll(
                () -> {
                    assertThatThrownBy(() -> Nickname.from(nullNickname));
                    assertThatThrownBy(() -> Nickname.from(blankNickname));
                }
        );
    }

    @Test
    @DisplayName("같은 값의 닉네임은 동일한 닉네임이다")
    void nicknameWithSameValueShouldBeEqual() {
        // given
        String name = "강산";
        Nickname nickname1 = Nickname.from(name);
        Nickname nickname2 = Nickname.from(name);

        // when
        boolean isEqual = nickname1.equals(nickname2);

        // then
        assertThat(isEqual).isTrue();
    }
}
