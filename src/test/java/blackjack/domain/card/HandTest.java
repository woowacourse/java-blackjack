package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    @DisplayName("에이스가 없을 때, 카드들의 숫자 합을 구한다.")
    @Test
    void calculateCardNumbersSum() {
        final Hand hand = new Hand(List.of(
                new Card(Number.TEN, Suit.DIAMOND),
                new Card(Number.EIGHT, Suit.CLOVER)
        ));

        final int actual = hand.sum();

        assertThat(actual).isEqualTo(18);
    }

    @DisplayName("소프트 핸드로 계산하는 경우 총합이 21을 넘지 않으면 에이스는 소프트 핸드로 계산한다.")
    @Test
    void calculateAceToEleven() {
        final Hand hand = new Hand(List.of(
                new Card(Number.ACE, Suit.DIAMOND),
                new Card(Number.EIGHT, Suit.CLOVER)
        ));

        final int actual = hand.sum();

        assertThat(actual).isEqualTo(19);
    }

    @DisplayName("소프트 핸드로 계산하는 경우 총합이 21을 넘으면 에이스는 1로 계산한다.")
    @Test
    void calculateAceToOne() {
        final Hand hand = new Hand(List.of(
                new Card(Number.ACE, Suit.DIAMOND),
                new Card(Number.EIGHT, Suit.CLOVER),
                new Card(Number.TEN, Suit.CLOVER)
        ));

        final int actual = hand.sum();

        assertThat(actual).isEqualTo(19);
    }

    @DisplayName("소프트 핸드로 계산하는 경우 총합이 21을 넘지 않으면 에이스는 소프트 핸드로 계산한다.")
    @Test
    void calculateAceToSoftHand() {
        final Hand hand = new Hand(List.of(
                new Card(Number.ACE, Suit.DIAMOND),
                new Card(Number.EIGHT, Suit.CLOVER)
        ));

        final int actual = hand.sum();

        assertThat(actual).isEqualTo(19);
    }

    @DisplayName("에이스가 여러 개일 때, 한 장만 소프트 핸드로 계산한다.")
    @Test
    void calculateOnlyOneAceToSoftHand() {
        final Hand hand = new Hand(List.of(
                new Card(Number.ACE, Suit.DIAMOND),
                new Card(Number.ACE, Suit.CLOVER)
        ));

        final int actual = hand.sum();

        assertThat(actual).isEqualTo(12);
    }

    @DisplayName("에이스가 여러 개일 때, 한 장만 소프트 핸드로 계산해도 21을 넘으면 모든 에이스를 1로 계산한다.")
    @Test
    void calculateAllAceToOne() {
        final Hand hand = new Hand(List.of(
                new Card(Number.ACE, Suit.DIAMOND),
                new Card(Number.ACE, Suit.CLOVER),
                new Card(Number.TEN, Suit.CLOVER)
        ));

        final int actual = hand.sum();

        assertThat(actual).isEqualTo(12);
    }
}
