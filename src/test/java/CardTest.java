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
    Card cardK = new Card(Rank.KING, Shape.CLUB);


    @Test
    @DisplayName("카드 점수 계산시 J, Q, K는 10으로 환산 2~10은 그대로 적용한다.")
    void calculate_card_score() {
        assertThat(card2.translateToScore()).isEqualTo(2);
        assertThat(cardJ.translateToScore()).isEqualTo(10);
        assertThat(cardQ.translateToScore()).isEqualTo(10);
        assertThat(cardK.translateToScore()).isEqualTo(10);
    }
}
