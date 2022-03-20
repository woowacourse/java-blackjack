package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {

    private final Ready ready = new Ready();

    @Test
    @DisplayName("카드가 한장만 있을 때 Ready인지 테스트")
    void drawOneCard() {
        final State state = ready.draw(Card.from(Suit.CLOVER, Denomination.EIGHT));

        Assertions.assertThat(state).isInstanceOf(Ready.class);
    }

    @Test
    @DisplayName("카드 두 장을 받을 때 Hit인지 테스트")
    void drawTwoCardsHit() {
        final State state = ready.draw(Card.from(Suit.CLOVER, Denomination.EIGHT))
                .draw(Card.from(Suit.CLOVER, Denomination.FIVE));

        Assertions.assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("카드 두 장을 받을 때 블랙잭인지 테스트")
    void drawTwoCardsBlackjack() {
        final State state = ready.draw(Card.from(Suit.CLOVER, Denomination.ACE))
                .draw(Card.from(Suit.CLOVER, Denomination.JACK));

        Assertions.assertThat(state).isInstanceOf(Blackjack.class);
    }

    @Test
    @DisplayName("턴이 끝난 상태로 나타내지 않는지 테스트")
    void isFinished() {
        assertThat(ready.isFinished()).isFalse();
    }

    @Test
    @DisplayName("턴이 진행중인 상태로 나타내지 않는지 테스트")
    void isRunning() {
        assertThat(ready.isRunning()).isFalse();
    }
}