package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.state.finished.Blackjack;
import blackjack.domain.state.running.Hit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class StartedTest {

    @Test
    void 첫_두장이_블랙잭이면_블랙잭이_만들어진다() {
        //given
        Card card1 = new Card(Suit.DIAMOND, Rank.ACE);
        Card card2 = new Card(Suit.DIAMOND, Rank.TEN);

        //when
        State state = Started.of(card1, card2);

        //then
        Assertions.assertThat(state).isInstanceOf(Blackjack.class);
    }

    @Test
    void 첫_두장이_블랙잭이_아니라면_HIT이_만들어진다() {
        //given
        Card card1 = new Card(Suit.DIAMOND, Rank.ACE);
        Card card2 = new Card(Suit.DIAMOND, Rank.NINE);

        //when
        State state = Started.of(card1, card2);

        //then
        Assertions.assertThat(state).isInstanceOf(Hit.class);
    }
}
