package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StayTest {

    private final Cards cards = new Cards(Set.of(Card.from(Suit.CLOVER, Denomination.JACK),
            Card.from(Suit.DIAMOND, Denomination.JACK)));

    @Test
    @DisplayName("카드를 그만 받는 상황일 때 다시 카드를 받으려 하는 경우 예외 발생 테스트")
    void drawIfStay() {
        final Status status = new Stay(cards);

        assertThatThrownBy(() -> status.draw(Card.from(Suit.HEART, Denomination.FIVE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드를 받을 수 없습니다.");
    }

    @Test
    @DisplayName("카드를 그만 받는 상황일 때 다시 카드를 그만 받으려 하는 경우 예외 발생 테스트")
    void stayIfStay() {
        final Status status = new Stay(cards);

        assertThatThrownBy(status::stay)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 결과입니다.");
    }
}