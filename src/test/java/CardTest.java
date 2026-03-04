import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("카드 점수 계산시 J, Q, K는 10으로 환산 2~10은 그대로 적용한다.")
    void calculate_card_score() {
        assertThat(Card.translateToScore("2")).isEqualTo(2);
        assertThat(Card.translateToScore("J")).isEqualTo(10);
        assertThat(Card.translateToScore("Q")).isEqualTo(10);
        assertThat(Card.translateToScore("K")).isEqualTo(10);
    }

    @Test
    @DisplayName("카드 점수 계산 예외 처리")
    void calculate_card_score_exception() {
        assertThatThrownBy(() -> Card.translateToScore("-1"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Card.translateToScore("B"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
