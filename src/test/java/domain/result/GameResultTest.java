package domain.result;

import static card.CardNumberType.ACE;
import static card.CardNumberType.JACK;
import static card.CardNumberType.KING;
import static card.CardNumberType.THREE;
import static card.CardNumberType.TWO;
import static card.CardShapeType.CLOVER;
import static org.assertj.core.api.Assertions.assertThat;

import card.Card;
import hand.Hand;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import participant.Dealer;
import participant.value.Money;
import participant.Player;
import result.GameStatus;

public class GameResultTest {
    Money zero = Money.ZERO;

    Dealer bustDealer = new Dealer(Hand.create(
            List.of(new Card(JACK, CLOVER), new Card(KING, CLOVER), new Card(TWO, CLOVER))));
    Player bustPlayer = new Player("mimi", Hand.create(
            List.of(new Card(JACK, CLOVER), new Card(KING, CLOVER), new Card(THREE, CLOVER))),
            zero);

    Dealer twentyHandDealer = new Dealer(Hand.create(
            List.of(new Card(JACK, CLOVER), new Card(KING, CLOVER))));
    Player twentyHandPlayer = new Player("mimi", Hand.create(
            List.of(new Card(JACK, CLOVER), new Card(KING, CLOVER))),
            zero);

    Dealer twentyOneHandDealer = new Dealer(Hand.create(
            List.of(new Card(JACK, CLOVER), new Card(KING, CLOVER), new Card(ACE, CLOVER))));
    Player twentyOneHandPlayer = new Player("mimi", Hand.create(
            List.of(new Card(JACK, CLOVER), new Card(KING, CLOVER), new Card(ACE, CLOVER)))
            ,zero);

    Dealer blackJackDealer = new Dealer(Hand.create(
            List.of(new Card(KING, CLOVER), new Card(ACE, CLOVER))));
    Player blackJackPlayer = new Player("mimi", Hand.create(
            List.of(new Card(KING, CLOVER), new Card(ACE, CLOVER))),
            zero);

    @DisplayName("플레이어가 버스트일 경우 플레이어의 패배이다")
    @Test
    void test1() {
        //given

        //when
        GameStatus gameStatus = bustPlayer.calculateResultAgainst(bustDealer);

        //then
        assertThat(gameStatus).isEqualTo(GameStatus.LOSE);
    }

    @DisplayName("딜러만 버스트일 경우 플레이어의 승리이다")
    @Test
    void test2() {
        //given

        //when
        GameStatus gameStatus = twentyHandPlayer.calculateResultAgainst(bustDealer);

        //then
        assertThat(gameStatus).isEqualTo(GameStatus.WIN);
    }

    @DisplayName("딜러와 풀래이어가 모두 블랙잭일 경우 무승부이다")
    @Test
    void test40() {
        //given

        //when
        GameStatus gameStatus = blackJackPlayer.calculateResultAgainst(blackJackDealer);

        //then
        assertThat(gameStatus).isEqualTo(GameStatus.DRAW);
    }

    @DisplayName("플레이어만 블랙잭일 경우 플레이어의 승리이다")
    @Test
    void test41() {
        //given

        //when
        GameStatus gameStatus = blackJackPlayer.calculateResultAgainst(twentyOneHandDealer);

        //then
        assertThat(gameStatus).isEqualTo(GameStatus.BLACKJACK_WIN);
    }

    @DisplayName("딜러만 블랙잭일 경우 플레이어의 패배이다")
    @Test
    void test42() {
        //given

        //when
        GameStatus gameStatus = twentyOneHandPlayer.calculateResultAgainst(blackJackDealer);

        //then
        assertThat(gameStatus).isEqualTo(GameStatus.LOSE);
    }

    @DisplayName("플레이어의 합이 더 높다면 플레이어의 승리이다")
    @Test
    void test4() {
        //given

        //when
        GameStatus gameStatus = twentyOneHandPlayer.calculateResultAgainst(twentyHandDealer);

        //then
        assertThat(gameStatus).isEqualTo(GameStatus.WIN);
    }

    @DisplayName("딜러의 합이 더 높다면 플레이어의 패배이다")
    @Test
    void test5() {
        //given

        //when
        GameStatus gameStatus = twentyHandPlayer.calculateResultAgainst(twentyOneHandDealer);

        //then
        assertThat(gameStatus).isEqualTo(GameStatus.LOSE);
    }

    @DisplayName("플레이어와 딜러의 합이 같다면 무승부이다")
    @Test
    void test6() {
        //given

        //when
        GameStatus gameStatus = twentyHandPlayer.calculateResultAgainst(twentyHandDealer);

        //then
        assertThat(gameStatus).isEqualTo(GameStatus.DRAW);
    }
}
