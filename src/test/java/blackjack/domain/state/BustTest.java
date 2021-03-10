package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BustTest {
    @DisplayName("버스트 상태에서 카드를 더 뽑는 경우 예외 처리한다.")
    @Test
    void bust_draw_test() {
        Bust bust = new Bust(new Cards(Arrays.asList(
                Card.of("하트", "A"),
                Card.of("스페이드", "10"),
                Card.of("클로버", "2")
        )));
        assertThatThrownBy(() -> bust.draw(Card.of("하트", "3")))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("카드를 뽑을 수 없습니다.");
    }
}