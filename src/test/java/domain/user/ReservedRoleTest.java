package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ReservedRoleTest {

    @Test
    @DisplayName("딜러는 예약어이다")
    void hasRoleSameNameWith() {
        assertThat(ReservedRole.hasRoleSameNameWith("딜러")).isEqualTo(true);
    }
}
