import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.Card;
import blackjack.domain.Rank;
import blackjack.domain.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    Card card2 = new Card(Rank.TWO, Shape.HEART);
    Card cardJ = new Card(Rank.JACK, Shape.SPADE);
    Card cardQ = new Card(Rank.QUEEN, Shape.DIAMOND);
    Card cardK = new Card(Rank.KING, Shape.CLOVER);

    /*Card cardException1 = new Card("B", "하트");
    Card cardException2 = new Card("-1", "스페이드");
*/

    @Test
    @DisplayName("카드 점수 계산시 J, Q, K는 10으로 환산 2~10은 그대로 적용한다.")
    void calculate_card_score() {
        assertThat(card2.getScore()).isEqualTo(2);
        assertThat(cardJ.getScore()).isEqualTo(10);
        assertThat(cardQ.getScore()).isEqualTo(10);
        assertThat(cardK.getScore()).isEqualTo(10);
    }

    /*@Test
    @DisplayName("카드 점수 계산 예외 처리")
    void calculate_card_score_exception() {
        assertThatThrownBy(() -> cardException1.getScore())
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> cardException2.getScore())
                .isInstanceOf(IllegalArgumentException.class);
    }*/
}
