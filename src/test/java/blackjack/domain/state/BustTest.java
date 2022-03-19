package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BustTest {

    private final Cards cards = new Cards(Set.of(Card.from(Suit.CLOVER, Denomination.JACK),
            Card.from(Suit.DIAMOND, Denomination.JACK), Card.from(Suit.CLOVER, Denomination.FIVE)));
    final Status status = new Bust(cards);

    @Test
    @DisplayName("버스트에서 카드를 더 받을 때 예외 발생 테스트")
    void drawIfBust() {

        assertThatThrownBy(() -> status.draw(Card.from(Suit.DIAMOND, Denomination.FIVE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드를 받을 수 없습니다.");
    }

    @Test
    @DisplayName("블랙잭에서 Stay 상태로 가려할 때 예외 발생 테스트")
    void drawIfStay() {
        assertThatThrownBy(status::stay)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 결과입니다.");
    }
}