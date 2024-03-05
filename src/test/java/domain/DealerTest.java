package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러는 카드를 한 장 뽑을 수 있다.")
    @Test
    void drawCard() {
        Card card = new Card(CardShape.SPADE, CardNumber.ACE);
        Dealer dealer = new Dealer(new Cards(List.of(card)));
        assertThat(dealer.drawCard()).isEqualTo(card);
    }
}
