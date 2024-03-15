package blackjack.domain.supplies;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.supplies.Rank.ACE;
import static blackjack.domain.supplies.Rank.EIGHT;
import static blackjack.domain.supplies.Rank.FIVE;
import static blackjack.domain.supplies.Rank.NINE;
import static blackjack.domain.supplies.Rank.QUEEN;
import static blackjack.domain.supplies.Rank.TWO;
import static blackjack.domain.supplies.Suit.CLUB;
import static blackjack.domain.supplies.Suit.DIAMOND;
import static blackjack.domain.supplies.Suit.HEART;
import static blackjack.domain.supplies.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("패")
public class HandTest {
    @Test
    @DisplayName("뽑은 카드들의 합을 구한다.")
    void cardsSumTest() {
        // given
        Hand hand = new Hand();
        int expectedScore = 9 + 5;

        // when
        hand.add(List.of(new Card(NINE, DIAMOND), new Card(FIVE, DIAMOND)));

        // then
        assertThat(hand.calculateScore()).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("카드 점수의 합 중 21과 가장 가까운 수를 반환한다.")
    void maxScoreTest() {
        // given
        Hand hand = new Hand();

        // when
        hand.add(new Card(NINE, SPADE));
        hand.add(new Card(QUEEN, CLUB));
        hand.add(new Card(ACE, CLUB));
        hand.add(new Card(ACE, HEART));

        // then
        assertThat(hand.calculateScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("에이스가 포함된 카드 점수의 합이 21을 넘을 경우 에이스를 1로 계산한 값을 반환한다.")
    void ifExceedLimitScoreThenAceIsOneTest() {
        // given
        Hand hand = new Hand();

        // when
        hand.add(new Card(NINE, SPADE));
        hand.add(new Card(QUEEN, CLUB));
        hand.add(new Card(ACE, CLUB));

        // then
        assertThat(hand.calculateScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("애이스가 포함된 카드 점수의 합이 21을 넘지 않을 경우 에이스를 11로 계산한 값을 반환한다.")
    void ifUnderLimitScoreThenAceIsElevenTest() {
        // given
        Hand hand = new Hand();

        // when
        hand.add(new Card(TWO, SPADE));
        hand.add(new Card(EIGHT, CLUB));
        hand.add(new Card(ACE, CLUB));

        // then
        assertThat(hand.calculateScore()).isEqualTo(21);
    }
}
