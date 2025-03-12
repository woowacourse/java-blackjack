package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class GameResultTest {

    private static final Card ACE = new Card(CardNumber.ACE, CardShape.CLOVER);
    private static final Card TEN = new Card(CardNumber.TEN, CardShape.CLOVER);

    @ParameterizedTest
    @MethodSource("finalResultExpectedData")
    @DisplayName("최종 수익을 종합해 반환한다")
    void 최종_수익을_종합해_반환한다(List<Card> dealerCard, List<Card> playerCard, int dealerExcepted, int playerExcepted) {
        Dealer dealer = new Dealer();
        dealer.addCards(dealerCard);

        Gambler gambler = new Gambler("비타", 1_000);
        gambler.addCards(playerCard);

        Map<Player, Integer> result = new GameResults(dealer, List.of(gambler)).getGameResults();

        assertAll(
                () -> assertThat(result.get(dealer)).isEqualTo(dealerExcepted),
                () -> assertThat(result.get(gambler)).isEqualTo(playerExcepted)
        );
    }

    @Test
    @DisplayName("참가자가 블랙잭인 경우 수익은 1.5배이다")
    void 참가자가_블랙잭인_경우_수익은_1_5배이다() {
        Dealer dealer = new Dealer();
        dealer.addCards(List.of(ACE));

        Gambler gambler = new Gambler("비타", 1_000);
        gambler.addCards(List.of(ACE, TEN));

        GameResults gameResults = new GameResults(dealer, List.of(gambler));

        Map<Player, Integer> result = gameResults.getGameResults();

        assertAll(
                () -> assertThat(result.get(dealer)).isEqualTo(-1_500),
                () -> assertThat(result.get(gambler)).isEqualTo(1_500)
        );
    }

    private static Stream<Arguments> finalResultExpectedData() {
        return Stream.of(
                Arguments.of(List.of(ACE), List.of(ACE, ACE), -1_000, 1_000),
                Arguments.of(List.of(ACE, ACE), List.of(ACE), 1_000, -1_000),
                Arguments.of(List.of(ACE), List.of(ACE), 0, 0)
        );
    }
}
