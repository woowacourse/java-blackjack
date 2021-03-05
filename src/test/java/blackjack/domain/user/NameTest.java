package blackjack.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class NameTest {
    @DisplayName("유저의 이름이 공백일 때 검증")
    @Test
    void whenUserNameEmpty() {
        //given
        String empty = "";
        //when
        //then
        assertThatThrownBy(() -> new User(new Name(empty)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름이 공백일 수 없습니다.");
    }
}