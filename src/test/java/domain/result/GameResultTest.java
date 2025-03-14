package domain.result;

import static card.CardNumberType.ACE;
import static card.CardNumberType.JACK;
import static card.CardNumberType.KING;
import static card.CardNumberType.THREE;
import static card.CardNumberType.TWO;
import static card.CardType.CLOVER;
import static org.assertj.core.api.Assertions.assertThat;

import card.Card;
import card.Hand;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import participant.Dealer;
import participant.value.Money;
import participant.Player;
import result.GameResult;
import result.GameStatus;

public class GameResultTest {
    Money zero = Money.createZero();

    Dealer bustDealer = new Dealer(new Hand(
            List.of(new Card(JACK, CLOVER), new Card(KING, CLOVER), new Card(TWO, CLOVER))));
    Player bustPlayer = new Player("mimi", new Hand(
            List.of(new Card(JACK, CLOVER), new Card(KING, CLOVER), new Card(THREE, CLOVER))),
            zero);

    Dealer twentyHandDealer = new Dealer(new Hand(
            List.of(new Card(JACK, CLOVER), new Card(KING, CLOVER))));
    Player twentyHandPlayer = new Player("mimi", new Hand(
            List.of(new Card(JACK, CLOVER), new Card(KING, CLOVER))),
            zero);

    Dealer twentyOneHandDealer = new Dealer(new Hand(
            List.of(new Card(JACK, CLOVER), new Card(KING, CLOVER), new Card(ACE, CLOVER))));
    Player twentyOneHandPlayer = new Player("mimi", new Hand(
            List.of(new Card(JACK, CLOVER), new Card(KING, CLOVER), new Card(ACE, CLOVER)))
            ,zero);

    Dealer blackJackDealer = new Dealer(new Hand(
            List.of(new Card(KING, CLOVER), new Card(ACE, CLOVER))));
    Player blackJackPlayer = new Player("mimi", new Hand(
            List.of(new Card(KING, CLOVER), new Card(ACE, CLOVER))),
            zero);

    @DisplayName("플레이어가 버스트일 경우 플레이어의 패배이다")
    @Test
    void test1() {
        //given
        GameResult gameResult = new GameResult();

        //when
        GameStatus gameStatus = gameResult.calculate(bustPlayer, bustDealer);

        //then
        assertThat(gameStatus).isEqualTo(GameStatus.LOSE);
    }

    @DisplayName("딜러만 버스트일 경우 플레이어의 승리이다")
    @Test
    void test2() {
        //given
        GameResult gameResult = new GameResult();

        //when
        GameStatus gameStatus = gameResult.calculate(twentyHandPlayer, bustDealer);

        //then
        assertThat(gameStatus).isEqualTo(GameStatus.WIN);
    }

    @DisplayName("딜러와 풀래이어가 모두 블랙잭일 경우 무승부이다")
    @Test
    void test40() {
        //given
        GameResult gameResult = new GameResult();

        //when
        GameStatus gameStatus = gameResult.calculate(blackJackPlayer, blackJackDealer);

        //then
        assertThat(gameStatus).isEqualTo(GameStatus.DRAW);
    }

    @DisplayName("플레이어만 블랙잭일 경우 플레이어의 승리이다")
    @Test
    void test41() {
        //given
        GameResult gameResult = new GameResult();

        //when
        GameStatus gameStatus = gameResult.calculate(blackJackPlayer, twentyOneHandDealer);

        //then
        assertThat(gameStatus).isEqualTo(GameStatus.BLACKJACK_WIN);
    }

    @DisplayName("딜러만 블랙잭일 경우 플레이어의 패배이다")
    @Test
    void test42() {
        //given
        GameResult gameResult = new GameResult();

        //when
        GameStatus gameStatus = gameResult.calculate(twentyOneHandPlayer, blackJackDealer);

        //then
        assertThat(gameStatus).isEqualTo(GameStatus.LOSE);
    }

    @DisplayName("플레이어의 합이 더 높다면 플레이어의 승리이다")
    @Test
    void test4() {
        //given
        GameResult gameResult = new GameResult();

        //when
        GameStatus gameStatus = gameResult.calculate(twentyOneHandPlayer, twentyHandDealer);

        //then
        assertThat(gameStatus).isEqualTo(GameStatus.WIN);
    }

    @DisplayName("딜러의 합이 더 높다면 플레이어의 패배이다")
    @Test
    void test5() {
        //given
        GameResult gameResult = new GameResult();

        //when
        GameStatus gameStatus = gameResult.calculate(twentyHandPlayer, twentyOneHandDealer);

        //then
        assertThat(gameStatus).isEqualTo(GameStatus.LOSE);
    }

    @DisplayName("플레이어와 딜러의 합이 같다면 무승부이다")
    @Test
    void test6() {
        //given
        GameResult gameResult = new GameResult();

        //when
        GameStatus gameStatus = gameResult.calculate(twentyHandPlayer, twentyHandDealer);

        //then
        assertThat(gameStatus).isEqualTo(GameStatus.DRAW);
    }
}
