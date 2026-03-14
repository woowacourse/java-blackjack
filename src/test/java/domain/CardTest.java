package domain;

import constant.Rank;
import constant.Suit;
import domain.card.Card;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CardTest {

    @Nested
    class CalculateScoreTest {

        // 10, A, etc

        @Nested
        class Success {

            @Test
            void J_Q_K_이면_10을_반환_해야_한다() {

                // given
                Card card1 = new Card(Rank.J, Suit.HEART);
                Card card2 = new Card(Rank.Q, Suit.HEART);
                Card card3 = new Card(Rank.K, Suit.HEART);

                // when
                int actual1 = card1.getScore();
                int actual2 = card2.getScore();
                int actual3 = card3.getScore();

                // then
                int expected = 10;
                Assertions.assertEquals(expected, actual1);
                Assertions.assertEquals(expected, actual2);
                Assertions.assertEquals(expected, actual3);
            }

            @Test
            void 에이스_이면_11을_반환_해야_한다() {

                // given
                Card card = new Card(Rank.ACE, Suit.CLOVER);

                // when
                int actual = card.getScore();

                // then
                Assertions.assertEquals(11, actual);
            }

            @ParameterizedTest
            @MethodSource("cardProvider")
            void 숫자라면_숫자를_반환_해야_한다(Card card, int expected) {

                // when
                int actual = card.getScore();

                // then
                Assertions.assertEquals(expected, actual);
            }

            static Stream<Arguments> cardProvider() {
                return Stream.of(
                    Arguments.of(new Card(Rank.FIVE, Suit.CLOVER), 5),
                    Arguments.of(new Card(Rank.TWO, Suit.CLOVER), 2),
                    Arguments.of(new Card(Rank.TEN, Suit.CLOVER), 10)
                );
            }
        }
    }
}
