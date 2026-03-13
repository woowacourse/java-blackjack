package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import blackjack.domain.participant.Nickname;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NicknameTest {

    @Test
    @DisplayName("닉네임 처리 테스트: 닉네임이 4~10자인 경우")
    void 정상_테스트_1() {
        assertDoesNotThrow(() ->  new Nickname("pobi"));
    }

    @Test
    @DisplayName("닉네임 처리 테스트: 닉네임이 4자 미만인 경우")
    void 예외_테스트_1() {
        assertThatThrownBy(() ->  new Nickname("pob"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("닉네임 처리 테스트: 닉네임이 10자 초과인 경우")
    void 예외_테스트_2() {
        assertThatThrownBy(() ->  new Nickname("jasonjasonj"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
