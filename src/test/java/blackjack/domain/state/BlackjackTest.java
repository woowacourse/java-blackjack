package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BlackjackTest {
    @DisplayName("블랙잭 상태에서 카드를 뽑는 경우 예외처리해준다.")
    @Test
    void black_draw_test() {
        Blackjack blackjack = new Blackjack(new Cards(Arrays.asList(
                Card.of("하트", "A"),
                Card.of("스페이드", "10")
        )));
        assertThatThrownBy(() -> blackjack.draw(Card.of("클로버", "2")))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("카드를 뽑을 수 없습니다.");
    }
}