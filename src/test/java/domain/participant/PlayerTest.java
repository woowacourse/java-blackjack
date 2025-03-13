package domain.participant;

import static domain.card.CardNumberType.*;
import static domain.card.CardType.*;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Hand;
import domain.result.GameResultStatus;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    Dealer bustDealer = new Dealer(new Hand(
            List.of(new Card(JACK, CLOVER), new Card(KING, CLOVER), new Card(TWO, CLOVER))));
    Player bustPlayer = new Player("mimi", new Hand(
            List.of(new Card(JACK, CLOVER), new Card(KING, CLOVER), new Card(THREE, CLOVER))));

    Dealer twentyHandDealer = new Dealer(new Hand(
            List.of(new Card(JACK, CLOVER), new Card(KING, CLOVER))));
    Player twentyHandPlayer = new Player("mimi", new Hand(
            List.of(new Card(JACK, CLOVER), new Card(KING, CLOVER))));

    Dealer twentyOneHandDealer = new Dealer(new Hand(
            List.of(new Card(JACK, CLOVER), new Card(KING, CLOVER), new Card(ACE, CLOVER))));
    Player twentyOneHandPlayer = new Player("mimi", new Hand(
            List.of(new Card(JACK, CLOVER), new Card(KING, CLOVER), new Card(ACE, CLOVER))));

    @DisplayName("딜러와 플레이어가 모두 버스트일 경우 플레이어 버스트가 우선이기 때문에, 플레이어의 패배이다")
    @Test
    void test1() {
        //given

        //when
        GameResultStatus gameResultStatus = bustPlayer.calculateScore(bustDealer);

        //then
        assertThat(gameResultStatus).isEqualTo(GameResultStatus.LOSE);
    }

    @DisplayName("딜러만 버스트일 경우 플레이어의 승리이다")
    @Test
    void test2() {
        //given

        //when
        GameResultStatus gameResultStatus = twentyHandPlayer.calculateScore(bustDealer);

        //then
        assertThat(gameResultStatus).isEqualTo(GameResultStatus.WIN);
    }

    @DisplayName("플레이어만 버스트일 경우 플레이어의 패배이다")
    @Test
    void test3() {
        //given

        //when
        GameResultStatus gameResultStatus = bustPlayer.calculateScore(twentyHandDealer);

        //then
        assertThat(gameResultStatus).isEqualTo(GameResultStatus.LOSE);
    }

    @DisplayName("딜러와 플레이어가 모두 버스트가 아니고, 플레이어의 합이 더 높다면 플레이어의 승리이다")
    @Test
    void test4() {
        //given

        //when
        GameResultStatus gameResultStatus = twentyOneHandPlayer.calculateScore(twentyHandDealer);

        //then
        assertThat(gameResultStatus).isEqualTo(GameResultStatus.WIN);
    }

    @DisplayName("딜러와 플레이어가 모두 버스트가 아니고, 딜러의 합이 더 높다면 플레이어의 패배이다")
    @Test
    void test5() {
        //given

        //when
        GameResultStatus gameResultStatus = twentyHandPlayer.calculateScore(twentyOneHandDealer);

        //then
        assertThat(gameResultStatus).isEqualTo(GameResultStatus.LOSE);
    }

    @DisplayName("딜러와 플레이어가 모두 버스트가 아니고, 플레이어와 딜러의 합이 같다면 무승부이다")
    @Test
    void test6() {
        //given

        //when
        GameResultStatus gameResultStatus = twentyHandPlayer.calculateScore(twentyHandDealer);

        //then
        assertThat(gameResultStatus).isEqualTo(GameResultStatus.DRAW);
    }
}
