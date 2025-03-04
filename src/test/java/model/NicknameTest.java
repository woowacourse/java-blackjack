package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NicknameTest {

    @Test
    @DisplayName("닉네임 객체를 생성이 잘 되는지")
    void newNickname() {

        // given
        final String valueInput = "pobi";
        final String expected = "pobi";

        // when
        final Nickname nickname = new Nickname(valueInput);

        // then
        Assertions.assertThat(nickname.getValue()).isEqualTo(expected);
    }
}