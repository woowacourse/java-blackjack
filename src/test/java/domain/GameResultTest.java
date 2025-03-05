package domain;

import static domain.Rank.EIGHT;
import static domain.Rank.JACK;
import static domain.Rank.KING;
import static domain.Rank.NINE;
import static domain.Rank.SEVEN;
import static domain.Rank.SIX;
import static domain.Rank.TEN;
import static domain.Suit.CLOVER;
import static domain.Suit.DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;

import fixture.CardFixture;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameResultTest {
    @ParameterizedTest
    @MethodSource("cardArguments")
    @DisplayName("딜러의 승패 여부를 판단할 수 있다")
    void testCalculateDealerGameResult(Set<Card> dealerCards, Set<Card> playerCards, GameResult expectedResult) {
        // given
        CardHand dealerCardHand = new CardHand(dealerCards);
        CardHand playerCardHand = new CardHand(playerCards);
        Dealer dealer = new Dealer(dealerCardHand);
        Player player = new Player("pobi", playerCardHand);
        // when
        GameResult gameResult = GameResult.calculateDealerGameResult(dealer, player);
        // then
        assertThat(gameResult).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> cardArguments() {
        return Stream.of(
                Arguments.arguments(Set.of(CardFixture.of(TEN, CLOVER), CardFixture.of(NINE, CLOVER)),
                        Set.of(CardFixture.of(EIGHT, CLOVER), CardFixture.of(SEVEN, CLOVER)),
                        GameResult.WIN),
                Arguments.arguments(Set.of(CardFixture.of(SIX, CLOVER), CardFixture.of(KING, CLOVER)),
                        Set.of(CardFixture.of(NINE, CLOVER), CardFixture.of(JACK, CLOVER)),
                        GameResult.LOSE),
                Arguments.arguments(Set.of(CardFixture.of(TEN, CLOVER), CardFixture.of(KING, CLOVER)),
                        Set.of(CardFixture.of(JACK, CLOVER), CardFixture.of(KING, DIAMOND)),
                        GameResult.PUSH));
    }
}
