package blackjack_statepattern.state;

import static blackjack_statepattern.Fixture.SPADES_ACE;
import static blackjack_statepattern.Fixture.SPADES_JACK;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack_statepattern.card.Card;
import blackjack_statepattern.card.Cards;
import blackjack_statepattern.card.Denomination;
import blackjack_statepattern.card.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackTest {

    @Test
    @DisplayName("블랙잭인 상태에서 카드를 받지 못한다.")
    void blackjackDraw() {
        Blackjack blackjack = new Blackjack(new Cards(SPADES_ACE, SPADES_JACK));
        assertThatThrownBy(
                () -> blackjack.draw(Card.of(Suit.SPADES, Denomination.TEN))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("블랙잭인 상태에서 수익을 계산할 수 있다.")
    void blackjackProfit() {
        Blackjack blackjack = new Blackjack(new Cards(SPADES_JACK, SPADES_ACE));
        int money = 1000;
        double profit = blackjack.profit(money);
        Assertions.assertThat(profit).isEqualTo(money * blackjack.earningRate());
    }
}
