package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Value;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StatusTest {
    @DisplayName("점수에 맞는 상태를 반환하는지 확인한다")
    @Test
    void testOf() {
        Hand hand1 = Hand.createEmptyHand();
        Hand hand2 = Hand.createEmptyHand();
        Hand hand3 = Hand.createEmptyHand();

        hand1.add(new Card(Suit.DIAMOND, Value.KING));
        hand1.add(new Card(Suit.DIAMOND, Value.ACE));

        hand2.add(new Card(Suit.DIAMOND, Value.SEVEN));
        hand2.add(new Card(Suit.DIAMOND, Value.ACE));

        hand3.add(new Card(Suit.DIAMOND, Value.KING));
        hand3.add(new Card(Suit.DIAMOND, Value.FIVE));
        hand3.add(new Card(Suit.DIAMOND, Value.SEVEN));

        assertThat(Status.of(hand1)).isEqualTo(Status.BLACKJACK);
        assertThat(Status.of(hand2)).isEqualTo(Status.PLAYING);
        assertThat(Status.of(hand3)).isEqualTo(Status.BURST);
    }
}
