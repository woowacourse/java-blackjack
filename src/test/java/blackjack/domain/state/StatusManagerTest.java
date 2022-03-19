package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StatusManagerTest {

    private final StatusManager statusManager = new StatusManager();

    @Test
    @DisplayName("블랙잭이 되는 경우 테스트")
    void isBlackjack() {
        final Status status = statusManager.start(Card.from(Suit.CLOVER, Denomination.JACK),
                Card.from(Suit.DIAMOND, Denomination.ACE));

        assertThat(status).isInstanceOf(Blackjack.class);
    }

    @Test
    @DisplayName("Hit 상태에서 카드를 더 받아 Bust가 되는 경우 테스트")
    void hitAndIsBust() {
        final Status status = statusManager.start(Card.from(Suit.CLOVER, Denomination.JACK),
                Card.from(Suit.DIAMOND, Denomination.EIGHT))
                .draw(Card.from(Suit.SPADE, Denomination.EIGHT));

        assertThat(status).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("Hit 상태에서 카드를 더 받아 Hit이 되는 경우 테스트")
    void hitAndIsHit() {
        final Status status = statusManager.start(Card.from(Suit.CLOVER, Denomination.JACK),
                Card.from(Suit.DIAMOND, Denomination.EIGHT))
                .draw(Card.from(Suit.SPADE, Denomination.TWO));

        assertThat(status).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("블랙잭이 되는 경우 카드를 더 받을 때 예외 발생 테스트")
    void drawIfBlackjack() {
        final Status status = statusManager.start(Card.from(Suit.CLOVER, Denomination.JACK),
                Card.from(Suit.DIAMOND, Denomination.ACE));

        assertThatThrownBy(() -> status.draw(Card.from(Suit.DIAMOND, Denomination.FIVE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드를 받을 수 없습니다.");
    }

    @Test
    @DisplayName("버스트가 되는 경우 카드를 더 받을 때 예외 발생 테스트")
    void drawIfBust() {
        final Status status = statusManager.start(Card.from(Suit.CLOVER, Denomination.JACK),
                Card.from(Suit.DIAMOND, Denomination.JACK));
        final Status bustStatus = status.draw(Card.from(Suit.DIAMOND, Denomination.EIGHT));

        assertThatThrownBy(() -> bustStatus.draw(Card.from(Suit.DIAMOND, Denomination.FIVE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드를 받을 수 없습니다.");
    }
}