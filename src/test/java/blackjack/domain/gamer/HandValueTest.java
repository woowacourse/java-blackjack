package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class HandValueTest {

    @Nested
    @DisplayName("핸드 점수 계산 테스트")
    class CalculateScoreTest {

        @Test
        @DisplayName("ACE가 없을때의 숫자 합을 계산한다.")
        void sumWithoutAceTest() {
            List<Card> cards = new ArrayList<>(List.of(
                    new Card(CardShape.CLOVER, CardNumber.KING),
                    new Card(CardShape.HEART, CardNumber.FIVE)
            ));

            HandValue handValue = new HandValue(cards);

            assertThat(handValue.getScore()).isEqualTo(15);
        }

        @Test
        @DisplayName("핸드 점수 합이 21을 초과하는 경우, ACE를 1로 계산한다.")
        void sumWithOneAceTest() {
            List<Card> cards = new ArrayList<>(List.of(
                    new Card(CardShape.HEART, CardNumber.ACE),
                    new Card(CardShape.CLOVER, CardNumber.KING),
                    new Card(CardShape.DIAMOND, CardNumber.KING)
            ));

            HandValue handValue = new HandValue(cards);

            assertThat(handValue.getScore()).isEqualTo(21);
        }

        @Test
        @DisplayName("핸드 점수 합이 21을 초과하지 않으면, ACE를 11로 계산한다.")
        void sumWithElevenAceTest() {
            List<Card> cards = new ArrayList<>(List.of(
                    new Card(CardShape.HEART, CardNumber.ACE),
                    new Card(CardShape.CLOVER, CardNumber.KING)
            ));

            HandValue handValue = new HandValue(cards);

            assertThat(handValue.getScore()).isEqualTo(21);
        }
    }

    @Nested
    @DisplayName("블랙잭 테스트")
    class BlackjackTest {

        @Test
        @DisplayName("카드가 2장이면서 핸드의 점수 합이 21점이면 블랙잭이다.")
        void isBlackjackTest() {
            List<Card> cards = new ArrayList<>(List.of(
                    new Card(CardShape.HEART, CardNumber.ACE),
                    new Card(CardShape.CLOVER, CardNumber.KING)
            ));

            HandValue handValue = new HandValue(cards);

            assertThat(handValue.isBlackjack()).isTrue();
        }

        @Test
        @DisplayName("카드가 2장이 아니면 블랙잭이 아니다")
        void isNotBlackjackTest() {
            List<Card> cards = new ArrayList<>(List.of(
                    new Card(CardShape.HEART, CardNumber.JACK),
                    new Card(CardShape.CLOVER, CardNumber.NINE),
                    new Card(CardShape.HEART, CardNumber.TWO)
            ));

            HandValue handValue = new HandValue(cards);

            assertThat(handValue.isBlackjack()).isFalse();
        }

        @Test
        @DisplayName("핸드 점수 합이 21점이 아니면 블랙잭이 아니다")
        void isNotBlackjackTest2() {
            List<Card> cards = new ArrayList<>(List.of(
                    new Card(CardShape.HEART, CardNumber.JACK),
                    new Card(CardShape.CLOVER, CardNumber.NINE)
            ));

            HandValue handValue = new HandValue(cards);

            assertThat(handValue.isBlackjack()).isFalse();
        }
    }

    @Nested
    @DisplayName("버스트 테스트")
    class BustTest {

        @Test
        @DisplayName("핸드 점수 합이 21점을 초과하면 버스트다.")
        void isBustTest() {
            List<Card> cards = new ArrayList<>(List.of(
                    new Card(CardShape.HEART, CardNumber.ACE),
                    new Card(CardShape.CLOVER, CardNumber.ACE),
                    new Card(CardShape.CLOVER, CardNumber.FIVE),
                    new Card(CardShape.CLOVER, CardNumber.JACK),
                    new Card(CardShape.DIAMOND, CardNumber.NINE)
            ));

            HandValue handValue = new HandValue(cards);

            assertThat(handValue.isBust()).isTrue();
        }

        @Test
        @DisplayName("핸드 점수 합이 21점을 초과하지 않으면 버스트가 아니다.")
        void isNotBustTest() {

            List<Card> cards = new ArrayList<>(List.of(
                    new Card(CardShape.HEART, CardNumber.ACE),
                    new Card(CardShape.DIAMOND, CardNumber.NINE)
            ));

            HandValue handValue = new HandValue(cards);

            assertThat(handValue.isBust()).isFalse();
        }
    }
}
