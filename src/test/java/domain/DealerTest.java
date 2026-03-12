package domain;

import domain.card.Deck;
import domain.card.RandomShuffleStrategy;
import domain.participant.Dealer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    public void 딜러는_카드의_합이_17이상_일때까지_카드를_뽑는다() {
        // given
        Deck deck = new Deck(new RandomShuffleStrategy());
        Dealer dealer = new Dealer(deck);
        int expected = 16;

        // when
        while (dealer.stay()) {
            dealer.playTurn(deck);
        }
        // then
        Assertions.assertThat(dealer.getScore()).isGreaterThan(expected);
    }
}
