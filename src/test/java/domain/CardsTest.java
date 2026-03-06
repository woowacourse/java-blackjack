package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    @DisplayName("카드 덱에서 2장을 드로우 했는지 정상 테스트")
    void 카드_덱_2장_빠져나가는지_테스트(){
        Cards cards = Cards.of();

        int drawSize = cards.drawInitialHand().size();

        int expect = 2;
        assertThat(drawSize).isEqualTo(expect);
    }

}