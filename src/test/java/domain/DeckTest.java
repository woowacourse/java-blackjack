package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("카드 덱에서 2장을 정상적으로 draw 하는지 테스트한다.")
    void 카드_덱_2장_draw_확인_테스트(){
        Deck deck = Deck.of(new NoShuffleStrategy());

        int drawSize = deck.drawInitialHand().size();

        int expect = 2;
        assertThat(drawSize).isEqualTo(expect);
    }

}