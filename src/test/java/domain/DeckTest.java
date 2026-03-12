package domain;

import static org.assertj.core.api.Assertions.*;

import domain.shuffle.NoShuffleStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.InputParser;
import view.mesage.ErrorMessage;

class DeckTest {

    @Test
    @DisplayName("카드 덱에서 2장을 정상적으로 draw 하는지 테스트한다.")
    void 카드_덱_2장_draw_확인_테스트() {
        Deck deck = Deck.of(new NoShuffleStrategy());

        int drawSize = deck.drawInitialHand().size();

        int expect = 2;
        assertThat(drawSize).isEqualTo(expect);
    }

    @Test
    @DisplayName("카드 덱에서 더이상 draw할 카드가 없을 시 에러를 반환한다.")
    void 카드_덱_크기가_0일시_draw_오류_테스트() {
        Deck deck = Deck.of(new NoShuffleStrategy());

        for (int i = 0; i < 26; i++) {
            deck.drawInitialHand();
        }

        assertThatIllegalArgumentException()
                .isThrownBy(deck::draw)
                .withMessage(ErrorMessage.EMPTY_DECK.getMessage());
    }
}