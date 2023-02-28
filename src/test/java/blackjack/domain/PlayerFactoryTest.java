package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerFactoryTest {

    @Test
    @DisplayName("입력된 참가자의 수가 2명 미만인 경우 예외 발생")
    void validateLength_under2() {
        // given
        String[] names = {"1"};

        // expect
        assertThatIllegalArgumentException().isThrownBy(() ->
                PlayerFactory.from(names)
        ).withMessage("[ERROR] 참가자의 수는 최소 2명에서 최대 8명이어야 합니다.");
    }

    @Test
    @DisplayName("입력된 참가자의 수가 8명 초과인 경우 예외 발생")
    void validateLength_over8() {
        // given
        String[] names = {"1","2","3","4","5","6","7","8","9"};

        // expect
        assertThatIllegalArgumentException().isThrownBy(() ->
                PlayerFactory.from(names)
        ).withMessage("[ERROR] 참가자의 수는 최소 2명에서 최대 8명이어야 합니다.");
    }
}
