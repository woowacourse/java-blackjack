package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.fixture.CardsFixture;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HandTest {

    @DisplayName("점수를 계산할 수 있다.")
    @ParameterizedTest
    @MethodSource("cardsAndScore")
    void calculateScoreTest(List<Card> cards, int expected) {
        Hand hand = new Hand(cards);

        assertThat(hand.calculateScore()).isEqualTo(expected);
    }

    static Stream<Arguments> cardsAndScore() {
        return Stream.of(Arguments.of(CardsFixture.BLACKJACK, 21),
                Arguments.of(CardsFixture.TWO_ACE, 12),
                Arguments.of(CardsFixture.CARDS_SCORE_16, 16));
    }

    @DisplayName("카드를 한 장 뽑는다")
    @Test
    void addTest() {
        Hand hand = new Hand(CardsFixture.CARDS_SCORE_16);

        Card additionalCard = new Card(Value.ACE, Shape.HEART);
        hand.add(additionalCard);

        assertThat(hand.getCards())
                .containsAll(CardsFixture.CARDS_SCORE_16)
                .contains(additionalCard)
                .hasSize(CardsFixture.CARDS_SCORE_16.size() + 1);
    }

    @DisplayName("카드의 버스트 상태를 알 수 있다.")
    @Nested
    class BustTest {

        @DisplayName("21점이 넘으면 버스트이다.")
        @Test
        void whenBusted_returnTrue() {
            Hand bustedHand = new Hand(CardsFixture.CARDS_SCORE_22);

            assertThat(bustedHand.isBusted()).isTrue();
        }

        @DisplayName("21점 이하 점수는 버스트가 아니다.")
        @Test
        void whenNotBusted_returnFalse() {
            Hand hand = new Hand(CardsFixture.CARDS_SCORE_21);

            assertThat(hand.isBusted()).isFalse();
        }
    }

    @DisplayName("카드의 블랙잭 상태를 판단할 수 있다.")
    @Nested
    class BlackjackTest {

        @DisplayName("21점이면서 2장의 카드라면 블랙잭이다.")
        @Test
        void whenBlackjack_returnTrue() {
            Hand blackjackHand = new Hand(CardsFixture.BLACKJACK);

            assertThat(blackjackHand.isBlackjack()).isTrue();
        }

        @DisplayName("21점 미만 점수는 블랙잭이 아니다.")
        @Test
        void whenUnderScore_returnFalse() {
            Hand blackjackHand = new Hand(List.of(
                    new Card(Value.KING, Shape.HEART),
                    new Card(Value.QUEEN, Shape.HEART)
            ));

            assertThat(blackjackHand.isBlackjack()).isFalse();
        }

        @DisplayName("21점이지만 3장의 카드라면 블랙잭이 아니다.")
        @Test
        void whenOverSize_returnFalse() {
            Hand blackjackHand = new Hand(CardsFixture.CARDS_SCORE_21);

            assertThat(blackjackHand.isBlackjack()).isFalse();
        }

        @DisplayName("21점 초과 점수는 블랙잭이 아니다.")
        @Test
        void whenOverScore_returnFalse() {
            Hand blackjackHand = new Hand(CardsFixture.CARDS_SCORE_22);

            assertThat(blackjackHand.isBlackjack()).isFalse();
        }
    }

    @DisplayName("빈 손패인지 확인할 수 있다.")
    @Nested
    class EmptyHandTest {

        @DisplayName("아무 카드도 존재하지 않으면 빈 손패이다.")
        @Test
        void whenEmptyHand_returnTrue() {
            Hand emptyHand = new Hand(Collections.emptyList());

            assertThat(emptyHand.isEmpty()).isTrue();
        }

        @DisplayName("카드가 한 장이라도 존재하면 빈 손패가 아니다.")
        @Test
        void whenCardExists_returnFalse() {
            Hand emptyHand = new Hand(List.of(new Card(Value.ACE, Shape.HEART)));

            assertThat(emptyHand.isEmpty()).isFalse();
        }
    }
}
