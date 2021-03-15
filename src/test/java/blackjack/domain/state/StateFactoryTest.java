package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StateFactoryTest {
    @Test
    @DisplayName("초기 Hit State 생성")
    void hitStateCreateTest() {
        State initialState = StateFactory.createInitialState(new Card(CardNumber.EIGHT, Shape.CLOVER),
                new Card(CardNumber.NINE, Shape.CLOVER));
        assertThat(initialState).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("초기 BlackJack State 생성")
    void blackjackStateCreateTest() {
        State initialState = StateFactory.createInitialState(new Card(CardNumber.ACE, Shape.CLOVER),
                new Card(CardNumber.KING, Shape.CLOVER));
        assertThat(initialState).isInstanceOf(Blackjack.class);
    }
}