package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class HandCardsTest {

    private static Stream<Arguments> provideCards() {
        return Stream.of(
                Arguments.of(List.of(new Card(CardNumber.TWO, CardSymbol.HEART), new Card(CardNumber.THREE, CardSymbol.HEART), new Card(CardNumber.FOUR, CardSymbol.HEART)), 9),
                Arguments.of(List.of(new Card(CardNumber.KING, CardSymbol.HEART), new Card(CardNumber.JACK, CardSymbol.HEART), new Card(CardNumber.QUEEN, CardSymbol.HEART)), 30),
                Arguments.of(List.of(new Card(CardNumber.ACE, CardSymbol.HEART), new Card(CardNumber.JACK, CardSymbol.HEART), new Card(CardNumber.QUEEN, CardSymbol.HEART)), 21),
                Arguments.of(List.of(new Card(CardNumber.ACE, CardSymbol.HEART), new Card(CardNumber.THREE, CardSymbol.HEART), new Card(CardNumber.ACE, CardSymbol.SPADE)), 15),
                Arguments.of(List.of(new Card(CardNumber.ACE, CardSymbol.HEART), new Card(CardNumber.ACE, CardSymbol.DIAMOND), new Card(CardNumber.ACE, CardSymbol.SPADE)), 13),
                Arguments.of(List.of(new Card(CardNumber.ACE, CardSymbol.HEART), new Card(CardNumber.ACE, CardSymbol.DIAMOND), new Card(CardNumber.ACE, CardSymbol.SPADE), new Card(CardNumber.ACE, CardSymbol.DIAMOND)), 14)
        );
    }

    @ParameterizedTest
    @MethodSource("provideCards")
    @DisplayName("updateCardScore() 는 점수를 업데이트 한다.")
    void calculate_player_cards_score(List<Card> cards, int expectedTotalScore) {
        // given & when
        HandCards handCards = new HandCards();
        cards.forEach(handCards::updateCardScore);

        // then
        Assertions.assertThat(handCards.getTotalScore()).isEqualTo(expectedTotalScore);
    }

    @ParameterizedTest
    @MethodSource("provideCards")
    @DisplayName("updateCardScore() 는 점수를 업데이트 한다.")
    void calculate_player_cards_score_with_ace(List<Card> cards, int expectedTotalScore) {
        // given & when
        HandCards handCards = new HandCards();
        cards.forEach(handCards::updateCardScore);

        // then
        Assertions.assertThat(handCards.getTotalScore()).isEqualTo(expectedTotalScore);
    }

    @Test
    @DisplayName("updateCardScore()는 카드를 하나씩 추가한다.")
    void add_card() {
        // given
        HandCards handCards = new HandCards();

        // when
        Card card = new Card(CardNumber.FIVE, CardSymbol.HEART);
        handCards.updateCardScore(card);

        // then
        Assertions.assertThat(handCards.getPlayerCards()).hasSize(1);
    }
}
