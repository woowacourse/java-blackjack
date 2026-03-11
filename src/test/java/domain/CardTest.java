package domain;

import static org.assertj.core.api.Assertions.assertThat;

import constant.Rank;
import constant.Suit;
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

        @Nested
        class Success {

            @Test
            void J_Q_K_이면_10을_반환_해야_한다() {

                // given
                Card card1 = new Card(Rank.J, Suit.HEART);
                Card card2 = new Card(Rank.Q, Suit.HEART);
                Card card3 = new Card(Rank.K, Suit.HEART);

                // when
                int actual1 = card1.calculateScore();
                int actual2 = card2.calculateScore();
                int actual3 = card3.calculateScore();

                // then
                int expected = 10;
                Assertions.assertEquals(10, actual1);
                Assertions.assertEquals(10, actual2);
                Assertions.assertEquals(10, actual3);
            }

            @Test
            void 에이스_이면_11을_반환_해야_한다() {

                // given
                Card card = new Card(Rank.ACE, Suit.CLOVER);

                // when
                int actual = card.calculateScore();

                // then
                Assertions.assertEquals(11, actual);
            }

            @ParameterizedTest
            @MethodSource("cardProvider")
            void 숫자라면_숫자를_반환_해야_한다(Card card, int expected) {

                // when
                int actual = card.calculateScore();

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

    @Nested
    class IsAceTest {

        @Nested
        class Success {

            @Test
            void 에이스_카드는_true를_반환해야_한다() {

                // given
                Card card = new Card(Rank.ACE, Suit.HEART);

                // when
                boolean actual = card.isAce();

                // then
                assertThat(actual).isTrue();
            }

            @Test
            void 에이스가_아닌_카드는_false를_반환해야_한다() {

                // given
                Card card = new Card(Rank.TEN, Suit.HEART);

                // when
                boolean actual = card.isAce();

                // then
                assertThat(actual).isFalse();
            }
        }
    }

    @Nested
    class EqualsAndHashCodeTest {

        @Nested
        class Success {

            @Test
            void 랭크와_무늬가_같으면_equals는_true다() {

                // given
                Card card = new Card(Rank.TEN, Suit.HEART);
                Card other = new Card(Rank.TEN, Suit.HEART);

                // when
                boolean actual = card.equals(other);

                // then
                assertThat(actual).isTrue();
            }

            @Test
            void 랭크와_무늬가_같으면_hashCode도_같다() {

                // given
                Card card = new Card(Rank.TEN, Suit.HEART);
                Card other = new Card(Rank.TEN, Suit.HEART);

                // when
                int actual = card.hashCode();

                // then
                assertThat(actual).isEqualTo(other.hashCode());
            }
        }
    }
}
