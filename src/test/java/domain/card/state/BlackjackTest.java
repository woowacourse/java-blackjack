package domain.card.state;

import static fixture.CardFixture.카드;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Cards;
import org.junit.jupiter.api.Test;

class BlackjackTest {
    @Test
    void 카드를_추가하면_예외가_발생한다() {
        CardState blackjack = new Blackjack(new Cards());

        assertThatThrownBy(() -> blackjack.receive(카드()))
                .isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("블랙잭 상태에서는 카드를 추가할 수 없습니다.");
    }
}
