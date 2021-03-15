package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StateFactoryTest {

    @Test
    @DisplayName("카드 2장을 받았을 때 합이 21인 경우 BlackJack 상태")
    void firstDrawBlackJack() {
        State state = StateFactory.draw(
                new Card(Shape.HEART, Denomination.ACE),
                new Card(Shape.DIAMOND, Denomination.KING)
        );
        assertThat(state).isInstanceOf(BlackJack.class);
    }

    @Test
    @DisplayName("카드 2장을 받았을 때 합이 21보다 작은 경우 Hit 상태")
    void firstDrawHit() {
        State state = StateFactory.draw(
                new Card(Shape.HEART, Denomination.ACE),
                new Card(Shape.DIAMOND, Denomination.NINE)
        );
        assertThat(state).isInstanceOf(Hit.class);
    }
}
