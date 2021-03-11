package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BetAmountTest {
    @DisplayName("서로다른 방식의 생성자 테스트")
    @Test
    void create() {
        assertThat(new BetAmount("10000").getAmount())
            .isEqualTo(new BetAmount(10000).getAmount());
    }

    @DisplayName("금액이 0 이하일시 에러 테스트")
    @Test
    void minusMoney() {
        assertThatThrownBy(() -> new BetAmount("-10000"))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new BetAmount(0))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("올바르지 않은 입력시 에러 테스트")
    @Test
    void validateInput() {
        assertThatThrownBy(() -> new BetAmount("-a"))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new BetAmount(null))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("get 테스트")
    @Test
    void getAmount() {
        assertThat(new BetAmount("5800").getAmount()).isEqualTo(5800);
    }
}
