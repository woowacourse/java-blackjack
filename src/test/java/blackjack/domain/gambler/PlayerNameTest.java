package blackjack.domain.gambler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerNameTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "pobi", "jason", "nickname12"
    })
    void 닉네임_길이_범위_테스트(String nickname) {
        // when // then
        Assertions.assertDoesNotThrow(() -> new PlayerName(nickname));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "nickname123", "nickname1234", "nickname12345"
    })
    void 닉네임_길이_경계값_초과시_예외를_발생시킨다(String nickname) {
        // when // then
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> new PlayerName(nickname))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 닉네임 길이는 10자를 초과할 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "", " ", "     "
    })
    void 닉네임이_빈값_입력_시_예외를_발생시킨다(String nickname) {
        // when // then
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> new PlayerName(nickname))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 닉네임은 빈 값을 입력할 수 없습니다.");
    }

}
