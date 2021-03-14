package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static blackjack.domain.Fixture.*;
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

    @ParameterizedTest(name = "{displayName}")
    @DisplayName("딜러는 ace를 11로 계산하고, 카드의 합계가 16이하일 때 1장 더 받을 수 있다.")
    @MethodSource("generateData")
    public void isAbleToReceiveCard(List<Card> inputCards, boolean result) {
        Cards cards = new Cards(inputCards);
        Dealer dealer = new Dealer();
        dealer.receiveCards(cards);
        assertThat(dealer.isAbleToReceiveCard()).isEqualTo(result);
    }

    @Test
    @DisplayName("딜러의 수익을 제대로 계산하는 지 테스트")
    public void calculateFinalBetProfit_1() {
        Player player1 = new Player("json");
        player1.initBetAmount(10000);
        Player player2 = new Player("pobi");
        player2.initBetAmount(20000);
        List<Player> players = Arrays.asList(
            player1,
            player2
        );
        Dealer dealer = new Dealer();
        player1.receiveCards(new Cards(CARDS_SCORE_20));
        player2.receiveCards(new Cards(CARDS_SCORE_19));
        dealer.receiveCards(new Cards(CARDS_SCORE_21));
        assertThat(dealer.calculateFinalBetProfit(players)).isEqualTo(new BetAmount(30000));
    }

    @Test
    @DisplayName("딜러의 수익을 제대로 계산하는 지 테스트")
    public void calculateFinalBetProfit_2() {
        Player player1 = new Player("json");
        player1.initBetAmount(10000);
        Player player2 = new Player("pobi");
        player2.initBetAmount(20000);
        List<Player> players = Arrays.asList(
                player1,
                player2
        );
        Dealer dealer = new Dealer();
        player1.receiveCards(new Cards(CARDS_SCORE_20)); // - 10000원
        player2.receiveCards(new Cards(CARDS_SCORE_BLACKJACK)); // + 10000원
        dealer.receiveCards(new Cards(CARDS_SCORE_21)); // 0원
        assertThat(dealer.calculateFinalBetProfit(players)).isEqualTo(new BetAmount(0));
    }
}
