package domain.player;

import static domain.fixture.UserFixture.FIVE_USERS_MAP;
import static domain.fixture.UserFixture.SIX_USERS_MAP;
import static domain.fixture.UserFixture.ZERO_USERS_MAP;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UsersTest {

    @Test
    void 유저_인원은_1인_이상_5인_이하여야_한다() {
        // when & then
        Assertions.assertThatCode(() -> Users.from(FIVE_USERS_MAP))
                .doesNotThrowAnyException();
    }

    @Test
    void 유저_인원이_0명이면_예외가_발생한다() {
        // when & then
        Assertions.assertThatThrownBy(() -> Users.from(ZERO_USERS_MAP))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 유저_인원이_5명_초과면_예외가_발생한다() {
        // when & then
        Assertions.assertThatCode(() -> Users.from(SIX_USERS_MAP))
                .isInstanceOf(IllegalArgumentException.class);
    }
}