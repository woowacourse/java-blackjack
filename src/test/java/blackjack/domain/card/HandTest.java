package blackjack.domain.card;

import static blackjack.domain.fixture.CardRepository.CLOVER2;
import static blackjack.domain.fixture.CardRepository.CLOVER4;
import static blackjack.domain.fixture.CardRepository.CLOVER5;
import static blackjack.domain.fixture.CardRepository.CLOVER6;
import static blackjack.domain.fixture.CardRepository.CLOVER9;
import static blackjack.domain.fixture.CardRepository.CLOVER_ACE;
import static blackjack.domain.fixture.CardRepository.CLOVER_KING;
import static blackjack.domain.fixture.CardRepository.CLOVER_QUEEN;
import static blackjack.domain.fixture.CardRepository.HEART_ACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import blackjack.domain.game.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class HandTest {

    @DisplayName("of 팩토리 메소드는 두개의 카드를 받아 Hand 인스턴스를 생성한다.")
    @Test
    void of_initsNewHandWithTwoCards() {
        CardDeck cardDeck = new CardDeck();
        Hand hand = Hand.of(cardDeck.pop(), cardDeck.pop());

        assertThat(hand).isNotNull();
    }

    @DisplayName("add 메서드로 새로운 카드를 추가할 수 있다.")
    @Test
    void add() {
        Hand hand = Hand.of(CLOVER4, CLOVER5);
        hand.add(CLOVER6);

        assertThat(hand.getCards())
                .contains(CLOVER4, CLOVER5, CLOVER6);
    }

    @DisplayName("add 메서드로 중복된 카드를 추가하려고 하면 예외가 발생한다.")
    @Test
    void add_throwsExceptionOnDuplicateCard() {
        Hand hand = Hand.of(CLOVER4, CLOVER5);

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> hand.add(CLOVER5))
                .withMessage("중복된 카드는 존재할 수 없습니다.");
    }

    @DisplayName("getScore 는 각 카드가 지닌 값들의 합을 합산하여 반환한다.")
    @Test
    void getScore() {
        Hand hand = Hand.of(CLOVER4, CLOVER5);

        Score actual = hand.getScore();
        Score expected = Score.valueOf(9);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("getScore 는 패에 ACE 가 포함되었을 때 최대한 버스트되지 않고 최대값이 되도록 ACE 값을 선택해야한다.")
    @Nested
    class getScore_withAce {

        @DisplayName("패가 ACE, 2 일경우 ACE 는 11로 취급된다.")
        @Test
        void getScore_withAceAnd2() {
            // given
            Hand hand = Hand.of(CLOVER_ACE, CLOVER2);

            // when
            Score actual = hand.getScore();
            Score expected = Score.valueOf(13);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("패가 ACE, ACE 일경우 첫번째 ACE는 11로, 두번째 ACE는 1로 취급된다.")
        @Test
        void getScore_withTwoAces() {
            // given
            Hand hand = Hand.of(CLOVER_ACE, HEART_ACE);

            // when
            Score actual = hand.getScore();
            Score expected = Score.valueOf(12);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("패가 KING, QUEEN, ACE 일경우 ACE는 1로 취급된다.")
        @Test
        void getScore_withKingQueenAndAce() {
            // given
            Hand hand = Hand.of(CLOVER_KING, CLOVER_QUEEN);
            hand.add(CLOVER_ACE);

            // when
            Score actual = hand.getScore();
            Score expected = Score.valueOf(21);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("패가 KING, 9, ACE, ACE 일경우 두 ACE는 모두 1로 취급된다.")
        @Test
        void getScore_withKingAceAnd9() {
            // given
            Hand hand = Hand.of(CLOVER_KING, CLOVER9);
            hand.add(CLOVER_ACE);
            hand.add(HEART_ACE);

            // when
            Score actual = hand.getScore();
            Score expected = Score.valueOf(21);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("패가 KING, QUEEN, ACE, ACE 일경우 두 ACE는 모두 1로 취급된다.")
        @Test
        void getScore_withKingQueenAndTwoAces() {
            // given
            Hand hand = Hand.of(CLOVER_KING, CLOVER_QUEEN);
            hand.add(CLOVER_ACE);
            hand.add(HEART_ACE);

            // when
            Score actual = hand.getScore();
            Score expected = Score.valueOf(22);

            // then
            assertThat(actual).isEqualTo(expected);
        }
    }
}
