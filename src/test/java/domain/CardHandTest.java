package domain;

import static domain.card.Rank.ACE;
import static domain.card.Rank.JACK;
import static domain.card.Rank.KING;
import static domain.card.Rank.NINE;
import static domain.card.Rank.QUEEN;
import static domain.card.Rank.SEVEN;
import static domain.card.Rank.THREE;
import static domain.card.Rank.TWO;
import static domain.card.Suit.CLOVER;
import static domain.card.Suit.DIAMOND;
import static domain.card.Suit.HEART;
import static domain.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardHand;
import fixture.CardFixture;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardHandTest {

    @Test
    @DisplayName("카드를 추가한다.")
    void testAddCard() {
        // given
        CardHand cardHand = new CardHand(Set.of(CardFixture.of(ACE, CLOVER)));
        // when
        cardHand.add(CardFixture.of(THREE, DIAMOND));
        // then
        CardHand expected = new CardHand(
                Set.of(CardFixture.of(THREE, DIAMOND), CardFixture.of(ACE, CLOVER)));
        assertThat(cardHand).isEqualTo(expected);
    }

    @Test
    @DisplayName("한 장의 카드 점수를 계산한다.")
    void testCalculateSingleCardScore() {
        // given
        CardHand cardHand = new CardHand(Set.of(CardFixture.of(TWO, CLOVER)));
        // when
        int score = cardHand.calculateScore();
        // then
        assertThat(score).isEqualTo(2);
    }

    @Test
    @DisplayName("여러 장의 카드 점수를 계산한다.")
    void testCalculateMultipleCardsScore() {
        // given
        CardHand cardHand = new CardHand(
                Set.of(CardFixture.of(TWO, HEART), CardFixture.of(KING, CLOVER)));
        // when
        int score = cardHand.calculateScore();
        // then
        assertThat(score).isEqualTo(12);
    }

    @ParameterizedTest
    @MethodSource("cardArguments")
    @DisplayName("에이스는 1또는 11로 계산된다.")
    void calculateScoreWithAce(Set<Card> cards, int expectedScore) {
        // given
        CardHand cardHand = new CardHand(cards);
        // when
        int score = cardHand.calculateScore();
        // then
        assertThat(score).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("총점이 21점이 넘어가는지 판단할 수 있다.")
    void testIsBust() {
        // given
        CardHand cardHand = new CardHand(
                Set.of(CardFixture.of(TWO, CLOVER), CardFixture.of(JACK, SPADE), CardFixture.of(QUEEN, SPADE)));
        // when & then
        assertThat(cardHand.isBust()).isTrue();
    }

    @Test
    @DisplayName("카드가 블랙잭인지 판단한다")
    void testIsBlackJack() {
        // given
        CardHand cardHand = new CardHand(
                Set.of(CardFixture.of(ACE, CLOVER), CardFixture.of(JACK, SPADE)));
        // when & then
        assertThat(cardHand.isBlackJack()).isTrue();
    }

    @Test
    @DisplayName("카드가 3장인 경우에 블랙잭인지 판단한다")
    void testIsNotBlackJack() {
        // given
        CardHand cardHand = new CardHand(
                Set.of(CardFixture.of(ACE, CLOVER), CardFixture.of(JACK, SPADE), CardFixture.of(QUEEN, SPADE)));
        // when & then
        assertThat(cardHand.isBlackJack()).isFalse();
    }

    private static Stream<Arguments> cardArguments() {
        return Stream.of(
                Arguments.arguments(Set.of(
                        CardFixture.of(ACE, CLOVER),
                        CardFixture.of(KING, CLOVER)), 21),
                Arguments.arguments(Set.of(
                        CardFixture.of(ACE, HEART),
                        CardFixture.of(ACE, CLOVER),
                        CardFixture.of(NINE, CLOVER)), 21),
                Arguments.arguments(Set.of(
                        CardFixture.of(ACE, HEART),
                        CardFixture.of(ACE, CLOVER),
                        CardFixture.of(ACE, SPADE),
                        CardFixture.of(NINE, CLOVER)), 12),
                Arguments.arguments(Set.of(
                        CardFixture.of(ACE, HEART),
                        CardFixture.of(ACE, CLOVER),
                        CardFixture.of(ACE, SPADE),
                        CardFixture.of(ACE, DIAMOND),
                        CardFixture.of(SEVEN, CLOVER)), 21));
    }
}
