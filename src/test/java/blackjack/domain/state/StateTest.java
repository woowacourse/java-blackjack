package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardValue;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.Test;

class StateTest {

    @Test
    void 블랙잭은_2장의_카드가_21점일때이다() {
        // given
        Player player = new Player("밀란");
        player.draw(new Card(CardValue.ACE, CardShape.CLOVER));
        player.draw(new Card(CardValue.JACK, CardShape.CLOVER));

        Dealer dealer = new Dealer();
        dealer.draw(new Card(CardValue.ACE, CardShape.SPADE));
        dealer.draw(new Card(CardValue.JACK, CardShape.SPADE));

        // when
        State state = State.from(player, dealer);

        // then
        assertThat(state).isEqualTo(State.BLACKJACK);
    }

    @Test
    void 두_장_이상의_카드가_21점일때는_블랙잭이_아니다() {
        // given
        Player player = new Player("밀란");
        player.draw(new Card(CardValue.ACE, CardShape.CLOVER));
        player.draw(new Card(CardValue.QUEEN, CardShape.CLOVER));
        player.draw(new Card(CardValue.JACK, CardShape.CLOVER));

        Dealer dealer = new Dealer();
        dealer.draw(new Card(CardValue.ACE, CardShape.SPADE));
        dealer.draw(new Card(CardValue.TWO, CardShape.SPADE));

        // when
        State state = State.from(player, dealer);

        // then
        assertThat(state).isNotEqualTo(State.BLACKJACK);
    }

}