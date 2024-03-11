package domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    @DisplayName("플레이어가 아니면 false를 반환한다.")
    void isNotPlayer() {
        Dealer dealer = new Dealer();

        assertThat(dealer.isPlayer()).isFalse();
    }
}
