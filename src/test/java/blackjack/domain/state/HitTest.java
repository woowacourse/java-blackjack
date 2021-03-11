package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.PlayerCards;
import blackjack.domain.player.BetAmount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HitTest {
    private static PlayerCards cards;

    @BeforeEach
    void setUp() {
        Card card = Card.valueOf(CardShape.CLUB, CardNumber.JACK);
        cards = new PlayerCards(card, card);
    }

    @DisplayName("hit 가 반환되는지 테스트")
    @Test
    void hit() {
        Hit hit = new Hit(cards);
        State state = hit.draw(Card.valueOf(CardShape.DIAMOND, CardNumber.ACE));
        assertThat(state).isInstanceOf(Hit.class);
    }

    @DisplayName("bust 가 반환되는지 테스트")
    @Test
    void bust() {
        Hit hit = new Hit(cards);
        State state = hit.draw(Card.valueOf(CardShape.DIAMOND, CardNumber.TWO));
        assertThat(state).isInstanceOf(Bust.class);
    }

    @DisplayName("stay 가 반환되는지 테스트")
    @Test
    void stay() {
        Hit hit = new Hit(cards);
        State state = hit.stay();
        assertThat(state).isInstanceOf(Stay.class);
    }

    @DisplayName("hit 시 에러가 나는지 테스트")
    @Test
    void profit() {
        Hit hit = new Hit(cards);
        State state = new Stay(new PlayerCards());
        assertThatThrownBy(() -> hit.profit(state, new BetAmount("10000")))
            .isInstanceOf(UnsupportedOperationException.class);
    }
}