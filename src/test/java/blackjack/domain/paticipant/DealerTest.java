package blackjack.domain.paticipant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    void 게임상태가_null인_경우_예외발생() {
        assertThatThrownBy(() -> new Dealer(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("게임 상태는 null이 들어올 수 없습니다.");
    }
}
