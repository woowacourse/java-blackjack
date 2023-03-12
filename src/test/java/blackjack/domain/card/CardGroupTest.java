package blackjack.domain.card;

import blackjack.domain.result.Score;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CardGroupTest {

    private final Card spadeAceCard = new Card(CardShape.SPADE, CardNumber.ACE);
    private final Card spadeEightCard = new Card(CardShape.SPADE, CardNumber.EIGHT);

    @Test
    @DisplayName("카드 그룹에 카드 하나를 추가하는 기능 테스트")
    void addCardTest() {
        final CardGroup cards = new CardGroup(spadeAceCard, spadeEightCard);
        final Card heartTwoCard = new Card(CardShape.HEART, CardNumber.TWO);

        cards.add(heartTwoCard);

        assertThat(cards).extracting("cards", InstanceOfAssertFactories.list(Card.class))
                .contains(spadeAceCard, spadeEightCard, heartTwoCard);
    }

    @Nested
    @DisplayName("카드의 점수를 구하는 테스트")
    static class GetScoreTest {

        private final Card spadeTwo = new Card(CardShape.SPADE, CardNumber.TWO);
        private final Card spadeThree = new Card(CardShape.SPADE, CardNumber.THREE);
        private CardGroup cards;

        @BeforeEach
        void setup() {
            cards = new CardGroup(spadeTwo, spadeThree);
        }

        @Test
        @DisplayName("Ace카드가 없는경우의 점수 확인 테스트")
        void scoreWithoutAceTest() {
            final Score expectedScore = new Score(2 + 3, 2);

            final Score actualScore = cards.getScore();

            assertThat(actualScore).isEqualTo(expectedScore);
        }

        @ParameterizedTest(name = "[{index}] 11 -> 1 로 변환된 ACE의 수 : {2}")
        @DisplayName("1장의 Ace카드가 포함된 경우 테스트")
        @MethodSource("provideSecondCardAndExpectedScore")
        void scoreWithOneAceTest(final Card secondInputCard, final Score expectedScore, final int reducedAceNumber) {
            final Card diamondAce = new Card(CardShape.DIAMOND, CardNumber.ACE);

            cards.add(diamondAce);
            cards.add(secondInputCard);
            final Score actualScore = cards.getScore();

            assertThat(actualScore).isEqualTo(expectedScore);
        }

        private static Stream<Arguments> provideSecondCardAndExpectedScore() {
            return Stream.of(
                    Arguments.of(new Card(CardShape.DIAMOND, CardNumber.FIVE), new Score(21, 4), 0),
                    Arguments.of(new Card(CardShape.DIAMOND, CardNumber.SIX), new Score(12, 4), 1)
            );
        }

        @ParameterizedTest(name = "[{index}] 11 -> 1 로 변환된 ACE의 수 : {2}")
        @DisplayName("2장의 Ace카드가 포함된 경우")
        @MethodSource("provideThirdCardAndExpectedScore")
        void scoreWithMultipleAceTest(final Card thirdCard, final Score expectedScore, final int reducedAceNumber) {
            final Card diamondAce = new Card(CardShape.DIAMOND, CardNumber.ACE);
            final Card heartAce = new Card(CardShape.HEART, CardNumber.ACE);

            cards.add(diamondAce);
            cards.add(heartAce);
            cards.add(thirdCard);
            final Score actualScore = cards.getScore();

            assertThat(actualScore).isEqualTo(expectedScore);
        }

        private static Stream<Arguments> provideThirdCardAndExpectedScore() {
            return Stream.of(
                    Arguments.of(new Card(CardShape.DIAMOND, CardNumber.FOUR), new Score(21, 5), 1),
                    Arguments.of(new Card(CardShape.DIAMOND, CardNumber.FIVE), new Score(12, 5), 2)
            );
        }

    }

}
