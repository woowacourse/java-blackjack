package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Symbol;
import blackjack.domain.vo.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                        new Card(Symbol.ACE, Shape.HEART),
                        new Card(Symbol.FIVE, Shape.HEART)
                ), true), // 합 : 16
                Arguments.of(Arrays.asList(
                        new Card(Symbol.ACE, Shape.HEART),
                        new Card(Symbol.SIX, Shape.HEART)
                ), false) // 합 : 17
        );
    }

    private static Stream<Arguments> generateCardAndResult() {
        return Stream.of(
                Arguments.of(Symbol.FOUR, Result.WIN),
                Arguments.of(Symbol.FIVE, Result.DRAW),
                Arguments.of(Symbol.SIX, Result.LOSE)
        );
    }

    @DisplayName("딜러는 ace를 11로 계산하고, 카드의 합계가 16이하일 때 1장 더 받을 수 있다.")
    @ParameterizedTest
    @MethodSource("generateData")
    public void isAbleToReceiveCard(List<Card> inputCards, boolean result) {
        Cards cards = new Cards(inputCards);
        Dealer dealer = new Dealer();

        dealer.receiveCards(cards);
        boolean isAbleToReceiveCard = dealer.isAbleToReceiveCard();

        assertThat(isAbleToReceiveCard).isEqualTo(result);
    }

    @DisplayName("딜러와 플레이어의 점수를 비교하고 결과를 반환한다.")
    @ParameterizedTest
    @MethodSource("generateCardAndResult")
    public void getStatisticResult(Symbol symbol, Result result) {
        Dealer dealer = new Dealer();
        List<Player> players = Arrays.asList(new Player("jason"));

        dealer.receiveCard(new Card(Symbol.FIVE, Shape.CLOVER));
        players.get(0).receiveCard(new Card(symbol, Shape.CLOVER));
        Map<Result, Long> resultMap = dealer.getStatisticsResult(players);
        long resultCounts = resultMap.get(result);

        assertThat(resultCounts).isEqualTo(1L);
    }
}
