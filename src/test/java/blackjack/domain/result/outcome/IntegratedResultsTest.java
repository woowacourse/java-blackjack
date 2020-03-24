package blackjack.domain.result.outcome;

import blackjack.domain.card.Card;
import blackjack.domain.card.component.Figure;
import blackjack.domain.card.component.Type;
import blackjack.domain.participant.*;
import blackjack.domain.participant.attribute.Money;
import blackjack.domain.participant.attribute.Name;
import blackjack.domain.result.ResultType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static blackjack.domain.card.Card.NULL_ERR_MSG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class IntegratedResultsTest {
    private static Dealer dealer;
    private static List<Name> names;
    private static List<Money> moneys;
    private static Players<Player> winOrLosePlayers;
    private static Players<BettingPlayer> bettingPlayers;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        names = Arrays.asList("쪼밀리", "라면").stream()
                .map(Name::new)
                .collect(Collectors.toList());

        moneys = Arrays.asList(1000, 2000).stream()
                .map(Money::new)
                .collect(Collectors.toList());

        winOrLosePlayers = PlayersFactory.createPlayers(names);

        bettingPlayers = PlayersFactory.createBettingPlayers(names, moneys);
    }

    @DisplayName("예외 테스트: IntegratedResults 생성자에 모두 null이 값 전달된 경우")
    @Test
    void test1() {
        assertThatThrownBy(() -> new IntegratedResults<>(null, null, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining(NULL_ERR_MSG);
    }

    @DisplayName("예외 테스트: IntegratedResults 생성자에 players가 null인 경우")
    @Test
    void test2() {
        assertThatThrownBy(() -> new IntegratedResults<>(null, dealer, new WinOrLoseResultResolver()))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining(NULL_ERR_MSG);

        assertThatThrownBy(() -> new IntegratedResults<>(null, dealer, new BettingResultResolver()))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining(NULL_ERR_MSG);
    }

    @DisplayName("예외 테스트: IntegratedResults 생성자에 dealer가 null인 경우")
    @Test
    void test3() {
        assertThatThrownBy(() -> new IntegratedResults<>(winOrLosePlayers, null, new WinOrLoseResultResolver()))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining(NULL_ERR_MSG);

        assertThatThrownBy(() -> new IntegratedResults<>(bettingPlayers, null, new BettingResultResolver()))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining(NULL_ERR_MSG);
    }

    @DisplayName("예외 테스트: IntegratedResults 생성자에 resolver가 null인 경우")
    @Test
    void test4() {
        assertThatThrownBy(() -> new IntegratedResults<>(winOrLosePlayers, dealer, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining(NULL_ERR_MSG);

        assertThatThrownBy(() -> new IntegratedResults<>(bettingPlayers, dealer, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining(NULL_ERR_MSG);
    }

    @DisplayName("승/무/패 게임 딜러 결과 확인")
    @Test
    void test5() {
        dealer.addCard(Card.of(Type.TEN, Figure.CLOVER));

        List<Player> players = winOrLosePlayers.getPlayers();
        players.get(0).addCard(Card.of(Type.FIVE, Figure.HEART));
        players.get(1).addCard(Card.of(Type.TEN, Figure.SPADE));

        IntegratedResults<Player, ResultType, Map<ResultType, Long>> results
                = new IntegratedResults<>(winOrLosePlayers, dealer, new WinOrLoseResultResolver());

        Map<ResultType, Long> actualResult = results.computeDealerResult();

        Map<ResultType, Long> expectedResult = new HashMap<>();
        expectedResult.put(ResultType.DRAW, 1L);
        expectedResult.put(ResultType.WIN, 1L);

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @DisplayName("배팅 게임 딜러 결과 확인")
    @Test
    void test6() {
        dealer.addCard(Card.of(Type.TEN, Figure.CLOVER));

        List<BettingPlayer> players = bettingPlayers.getPlayers();
        players.get(0).addCard(Card.of(Type.FIVE, Figure.HEART));
        players.get(1).addCard(Card.of(Type.TEN, Figure.SPADE));

        IntegratedResults<BettingPlayer, Double, Double> results
                = new IntegratedResults<>(bettingPlayers, dealer, new BettingResultResolver());

        Double actualResult = results.computeDealerResult();

        Double expectedResult = 1000D;

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
