package domain.card;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Hand는 ")
class HandTest {

    @Test
    void 카드를_저장_할_수_있다() {
        // given
        Hand hand = new Hand();

        // when
        hand.addCard(new Card(Denomination.EIGHT, Suit.DIAMOND));

        // then
        Assertions.assertThat(hand.toList().size()).isEqualTo(1);
    }

    @Test
    void 점수를_계산할_수_있다() {
        // given
        Hand hand = new Hand();

        // when
        hand.addCard(new Card(Denomination.EIGHT, Suit.DIAMOND));

        // then
        Assertions.assertThat(hand.getScore()).isEqualTo(8);
    }

    @Test
    void ACE를_포함해서_블랙잭보다_같거나_작은수로_계산할_수_있다() {
        // given
        Hand hand = new Hand();

        // when
        hand.addCard(new Card(Denomination.JACK, Suit.DIAMOND));
        hand.addCard(new Card(Denomination.ACE, Suit.DIAMOND));

        // then
        Assertions.assertThat(hand.getScore()).isEqualTo(21);
    }

}