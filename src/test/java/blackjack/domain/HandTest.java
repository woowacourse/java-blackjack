package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.card.pattern.Denomination;
import blackjack.domain.card.pattern.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {

    @Test
    @DisplayName("카드 한 장을 저장한다.")
    void saveCard() {
        Hand hand = new Hand();
        hand.save(new Card(Suit.CLOVER, Denomination.FIVE));
        assertThat(hand.getHand().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Ace가 있는 상태로 total point를 계산한다.")
    void sumTotalPointWithAce() {
        Hand hand = new Hand();

        hand.save(new Card(Suit.CLOVER, Denomination.ACE));
        hand.save(new Card(Suit.DIAMOND, Denomination.JACK));

        assertThat(hand.calculateTotalPoint()).isEqualTo(21);
    }

    @Test
    @DisplayName("Ace가 있는 상태로 total point가 21을 초과하는 경우 total point를 계산한다.")
    void sumTotalPointWithAceOverMax() {
        Hand hand = new Hand();

        hand.save(new Card(Suit.CLOVER, Denomination.ACE));
        hand.save(new Card(Suit.DIAMOND, Denomination.JACK));
        hand.save(new Card(Suit.DIAMOND, Denomination.ACE));

        assertThat(hand.calculateTotalPoint()).isEqualTo(12);
    }
}
