package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.user.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StateFactoryTest {
    @Test
    @DisplayName("카드에 따라 state 객체를 반환한다.")
    void createState1() {
        Cards cards = new Cards(
                new Card(Suit.CLOVER, Denomination.JACK),
                new Card(Suit.HEART, Denomination.TEN));
        State state = StateFactory.generateStateByCards(cards);

        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("카드에 따라 state 객체를 반환한다.")
    void createState2() {
        Cards cards = new Cards(
                new Card(Suit.CLOVER, Denomination.ACE),
                new Card(Suit.HEART, Denomination.ACE));
        State state = StateFactory.generateStateByCards(cards);

        assertThat(state).isInstanceOf(Blackjack.class);
    }
}
