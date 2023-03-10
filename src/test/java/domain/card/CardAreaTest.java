package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static domain.card.CardShape.CLOVER;
import static domain.card.CardShape.DIAMOND;
import static domain.card.CardValue.*;
import static domain.fixture.CardAreaFixture.withTwoCard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("CardArea 은")
class CardAreaTest {

    @Test
    void 카드를_두장만_받아서_생성된다() {
        // when & then
        assertDoesNotThrow(() -> CardArea.initialWithTwoCard(
                new Card(DIAMOND, TWO),
                new Card(DIAMOND, TWO)
        ));
    }

    @Test
    void 카드를_추가할_수_있다() {
        // given
        final CardArea cardArea = withTwoCard(TEN, EIGHT);

        // when
        final int beforeSize = cardArea.cards().size();
        cardArea.addCard(new Card(CLOVER, FOUR));

        // then
        assertThat(cardArea.cards().size()).isEqualTo(beforeSize + 1);
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class 카드_점수_합_테스트 {

        @ParameterizedTest(name = "카드 목록이 {0} 일 때, 총합은 {1}다.")
        @CsvSource(value = {
                "TWO,THREE,5",
                "FIVE,SIX,11",
                "TWO,TWO,4",
                "TEN,TEN,20",
        })
        void 자신이_가진_카드의_합을_구할_수_있다(final CardValue first, final CardValue second, final int sum) {
            // given
            final CardArea cardArea = withTwoCard(first, second);

            // when & then
            assertThat(cardArea.calculate().value()).isEqualTo(sum);
        }

        @ParameterizedTest(name = "킹, 퀸, 잭은 10으로 계산한다")
        @CsvSource(value = {
                "KING,QUEEN,20",
                "KING,JACK,20",
                "QUEEN,JACK,20",
                "KING,THREE,13",
                "QUEEN,THREE,13",
                "JACK,SIX,16",
        })
        void 킹_퀸_잭은_10으로_계산한다(final CardValue first, final CardValue second, final int sum) {
            // given
            final CardArea cardArea = withTwoCard(first, second);

            // when & then
            assertThat(cardArea.calculate().value()).isEqualTo(sum);
        }

        @ParameterizedTest(name = "[{index}] ACE 는 해당 값을 포함한 전체 점수가 21 이하인 경우 11로 계산한다.")
        @MethodSource("containsAceCardArea")
        void ACE_는_이전까지의_총합이_10_이하면_11로_계산한다(final CardArea cardArea, final int totalScore) {
            // then
            assertThat(cardArea.calculate().value()).isEqualTo(totalScore);
        }

        Stream<Arguments> containsAceCardArea() {

            // 10 + [11] = 21
            final CardArea cardArea1 = withTwoCard(TEN, ACE);

            // 10 + 10 + [1] = 21
            final CardArea cardArea2 = withTwoCard(JACK, TEN);
            cardArea2.addCard(new Card(CLOVER, ACE));

            // [11] + 9 + [1] = 21
            final CardArea cardArea3 = withTwoCard(ACE, NINE);
            cardArea3.addCard(new Card(CLOVER, ACE));

            // [11] + 6 + 3 = 20
            final CardArea cardArea4 = withTwoCard(SIX, THREE);
            cardArea4.addCard(new Card(CLOVER, ACE));

            // [11] + 10 = 21
            final CardArea cardArea5 = withTwoCard(ACE, TEN);

            // 10 + [1] + 7 = 18
            final CardArea cardArea6 = withTwoCard(TEN, ACE);
            cardArea6.addCard(new Card(CardShape.SPADE, SEVEN));

            // [11] + [1] + [1] = 13
            final CardArea cardArea7 = withTwoCard(ACE, ACE);
            cardArea7.addCard(new Card(CardShape.SPADE, ACE));

            return Stream.of(
                    Arguments.of(cardArea1, 21),
                    Arguments.of(cardArea2, 21),
                    Arguments.of(cardArea3, 21),
                    Arguments.of(cardArea4, 20),
                    Arguments.of(cardArea5, 21),
                    Arguments.of(cardArea6, 18),
                    Arguments.of(cardArea7, 13)
            );
        }

    }

    @Nested
    class Hit_가능한_상태_테스트 {

        @Test
        void 총합이_20_이하면_카드를_더_받을_수_있는_상태이다() {
            // given
            final CardArea cardArea = withTwoCard(TEN, TEN);

            // when & then
            assertTrue(cardArea.canMoreCard());
        }

        @Test
        void 총합이_21_이상이면_카드를_더_받을_수_없는_상태이다() {
            // given
            final CardArea cardArea = withTwoCard(TEN, ACE);

            // when & then
            assertFalse(cardArea.canMoreCard());
        }
    }

    @Nested
    class 버스트_테스트 {

        @Test
        void 총합이_21_초과이면_버스트_이다() {
            // given
            final CardArea cardArea = withTwoCard(TEN, TEN);
            cardArea.addCard(new Card(CardShape.DIAMOND, TEN));

            // when & then
            assertTrue(cardArea.isBust());
        }

        @Test
        void 총합이_21_이하이면_버스트_아니다() {
            // given
            final CardArea cardArea = withTwoCard(TEN, ACE);

            // when & then
            assertFalse(cardArea.isBust());
        }
    }

    @Nested
    class 블랙잭_테스트 {

        @ParameterizedTest(name = "카드가 2장이면서 21인 경우 블랙잭이다. 예를 들어 {0} 과 {1} 이라면 블랙잭이다")
        @CsvSource({
                "ACE,TEN",
                "ACE,JACK",
                "QUEEN,ACE",
        })
        void 카드가_2장이면서_21인_경우_블랙잭이다(final CardValue cardValue1, final CardValue cardValue2) {
            // given
            final CardArea cardArea = withTwoCard(cardValue1, cardValue2);

            // when & then
            assertThat(cardArea.isBlackJack()).isTrue();
        }

        @Test
        void 카드가_2장이_아닌_경우_21이라도_블랙잭은_아니다() {
            // given
            final CardArea cardArea = withTwoCard(TWO, ACE);
            cardArea.addCard(new Card(DIAMOND, EIGHT));

            // when & then
            assertThat(cardArea.isBlackJack()).isFalse();
        }
    }
}
