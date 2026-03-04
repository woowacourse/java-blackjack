import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    Card card2 = new Card("2", "하트");
    Card cardJ = new Card("J", "스페이드");
    Card cardQ = new Card("Q", "다이아몬드");
    Card cardK = new Card("K", "클로버");

    Card cardException1 = new Card("B", "하트");
    Card cardException2 = new Card("-1", "스페이드");


    @Test
    @DisplayName("카드 점수 계산시 J, Q, K는 10으로 환산 2~10은 그대로 적용한다.")
    void calculate_card_score() {
        assertThat(card2.translateToScore()).isEqualTo(2);
        assertThat(cardJ.translateToScore()).isEqualTo(10);
        assertThat(cardQ.translateToScore()).isEqualTo(10);
        assertThat(cardK.translateToScore()).isEqualTo(10);
    }

    @Test
    @DisplayName("카드 점수 계산 예외 처리")
    void calculate_card_score_exception() {
        assertThatThrownBy(() -> cardException1.translateToScore())
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> cardException2.translateToScore())
                .isInstanceOf(IllegalArgumentException.class);
    }
}
