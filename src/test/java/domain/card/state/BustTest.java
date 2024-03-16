package domain.card.state;

import static fixture.CardFixture.카드;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Cards;
import org.junit.jupiter.api.Test;

class BustTest {
    @Test
    void 카드를_추가하면_예외가_발생한다() {
        CardState bust = new Bust(new Cards());

        assertThatThrownBy(() -> bust.receive(카드()))
                .isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("버스트 상태에서는 카드를 추가할 수 없습니다.");
    }
}
