package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.card.Rank.ACE;
import static blackjack.domain.card.Rank.EIGHT;
import static blackjack.domain.card.Rank.FIVE;
import static blackjack.domain.card.Rank.NINE;
import static blackjack.domain.card.Rank.QUEEN;
import static blackjack.domain.card.Rank.TWO;
import static blackjack.domain.card.Suit.CLUB;
import static blackjack.domain.card.Suit.DIAMOND;
import static blackjack.domain.card.Suit.HEART;
import static blackjack.domain.card.Suit.SPADE;
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
        hand.add(List.of(Card.of(NINE, DIAMOND), Card.of(FIVE, DIAMOND)));

        // then
        assertThat(hand.score().toInt()).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("카드 점수의 합 중 21과 가장 가까운 수를 반환한다.")
    void maxScoreTest() {
        // given
        Hand hand = new Hand();

        // when
        hand.add(Card.of(NINE, SPADE));
        hand.add(Card.of(QUEEN, CLUB));
        hand.add(Card.of(ACE, CLUB));
        hand.add(Card.of(ACE, HEART));

        // then
        assertThat(hand.score().toInt()).isEqualTo(21);
    }

    @Test
    @DisplayName("에이스가 포함된 카드 점수의 합이 21을 넘을 경우 에이스를 1로 계산한 값을 반환한다.")
    void ifExceedLimitScoreThenAceIsOneTest() {
        // given
        Hand hand = new Hand();

        // when
        hand.add(Card.of(NINE, SPADE));
        hand.add(Card.of(QUEEN, CLUB));
        hand.add(Card.of(ACE, CLUB));

        // then
        assertThat(hand.score().toInt()).isEqualTo(20);
    }

    @Test
    @DisplayName("애이스가 포함된 카드 점수의 합이 21을 넘지 않을 경우 에이스를 11로 계산한 값을 반환한다.")
    void ifUnderLimitScoreThenAceIsElevenTest() {
        // given
        Hand hand = new Hand();

        // when
        hand.add(Card.of(TWO, SPADE));
        hand.add(Card.of(EIGHT, CLUB));
        hand.add(Card.of(ACE, CLUB));

        // then
        assertThat(hand.score().toInt()).isEqualTo(21);
    }
}
