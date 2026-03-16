package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NicknameTest {

    @DisplayName("이름이 존재하지 않을 경우 예외가 발생한다.")
    @Test
    void validateEmptyName() {
        // given
        String playerNickname = "";

        // when & then
        assertThatThrownBy(() -> Nickname.from(playerNickname))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이름은 공백이 될 수 없습니다.");
    }

    @Test
    @DisplayName("이름에 공백만 입력될 경우 예외가 발생한다.")
    void validateBlankName() {
        // given
        String playerNickname = " ";

        // when & then
        assertThatThrownBy(() -> Nickname.from(playerNickname))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이름은 공백이 될 수 없습니다.");
    }

    @Test
    @DisplayName("딜러와 중복된 닉네임이 존재한다면 예외가 발생한다.")
    void dealerNicknameException() {
        // given
        String dealerNickname = "딜러";

        // when
        assertThatThrownBy(() -> Nickname.from(dealerNickname))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("딜러와 겹치는 닉네임을 입력할 수 없습니다. 다시 입력해주세요.");
    }
}
