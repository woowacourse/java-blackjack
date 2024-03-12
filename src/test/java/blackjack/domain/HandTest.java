package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    @DisplayName("핸드에 들어있는 카드의 점수를 계산한다")
    @Test
    public void calculate() {
        Hand hand = Hand.from(new Card(Suit.CLOVER, Denomination.TWO), new Card(Suit.DIAMOND, Denomination.JACK));

        assertThat(hand.calculate()).isEqualTo(Score.from(12));
    }

    @DisplayName("핸드에 들어있는 에이스의 점수를 11로 더하여 계산한다")
    @Test
    public void calculateAceToEleven() {
        Hand hand = Hand.from(new Card(Suit.CLOVER, Denomination.ACE), new Card(Suit.DIAMOND, Denomination.JACK));

        assertThat(hand.calculate()).isEqualTo(Score.from(21));
    }

    @DisplayName("다른 카드와 ACE를 11로 계산했을 때 21이 넘는다면 ACE를 1로 계산한다")
    @Test
    public void calculateAceToOne() {
        Hand hand = Hand.from(new Card(Suit.CLOVER, Denomination.ACE), new Card(Suit.DIAMOND, Denomination.JACK),
                new Card(Suit.SPADE, Denomination.KING));

        assertThat(hand.calculate()).isEqualTo(Score.from(21));
    }

    @DisplayName("핸드에 에이스가 두 장 이상이면 한 장만 11점으로 계산한다")
    @Test
    public void calculateTotalScore() {
        Hand hand = Hand.from(new Card(Suit.CLOVER, Denomination.ACE), new Card(Suit.DIAMOND, Denomination.ACE));

        assertThat(hand.calculate()).isEqualTo(Score.from(12));
    }
}
