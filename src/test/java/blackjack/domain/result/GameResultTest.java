package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.player.BettingMoney;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {

    @DisplayName("딜러와 플레이어들의 게임 결과 테스트 - 딜러 1승 1패")
    @Test
    void judgeGameResultWithGamers_1() {
        // given
        Gamer gamer1 = new Gamer(new Name("pobi"), Cards.of(
                Card.of(Denomination.KING, Shape.CLUBS),
                Card.of(Denomination.SEVEN, Shape.CLUBS)
        ));

        Gamer gamer2 = new Gamer(new Name("jason"), Cards.of(
                Card.of(Denomination.KING, Shape.CLUBS),
                Card.of(Denomination.TWO, Shape.CLUBS)
        ));

        Dealer dealer = new Dealer(Cards.of(
                Card.of(Denomination.KING, Shape.CLUBS),
                Card.of(Denomination.SIX, Shape.CLUBS)
        ));

        // when
        GameResult gameResult = GameResult.of(dealer, Arrays.asList(gamer1, gamer2));

        // then
        assertThat(gameResult.findByPlayer(dealer))
                .contains(ResultType.WIN, ResultType.LOSE);
        assertThat(gameResult.findByPlayer(gamer1))
                .containsExactly(ResultType.WIN);
        assertThat(gameResult.findByPlayer(gamer2))
                .containsExactly(ResultType.LOSE);
    }

    @DisplayName("딜러와 플레이어들의 게임 결과 테스트 - 딜러 2무")
    @Test
    void judgeGameResultWithGamers_2() {
        // given
        Gamer gamer1 = new Gamer(new Name("pobi"), Cards.of(
                Card.of(Denomination.KING, Shape.CLUBS),
                Card.of(Denomination.SIX, Shape.CLUBS)
        ));

        Gamer gamer2 = new Gamer(new Name("jason"), Cards.of(
                Card.of(Denomination.KING, Shape.CLUBS),
                Card.of(Denomination.SIX, Shape.CLUBS)
        ));

        Dealer dealer = new Dealer(Cards.of(
                Card.of(Denomination.KING, Shape.CLUBS),
                Card.of(Denomination.SIX, Shape.CLUBS)
        ));

        // when
        GameResult gameResult = GameResult.of(dealer, Arrays.asList(gamer1, gamer2));

        // then
        assertThat(gameResult.findByPlayer(dealer))
                .containsExactly(ResultType.DRAW, ResultType.DRAW);
        assertThat(gameResult.findByPlayer(gamer1))
                .containsExactly(ResultType.DRAW);
        assertThat(gameResult.findByPlayer(gamer2))
                .containsExactly(ResultType.DRAW);
    }

    @DisplayName("플레이어가 블랙잭이며 딜러가 블랙잭이 아닐 때")
    @Test
    void 게임_결과_수익_확인1() {
        Gamer gamer1 = new Gamer(new Name("pobi"), Cards.of(
                Card.of(Denomination.ACE, Shape.CLUBS),
                Card.of(Denomination.JACK, Shape.CLUBS)
        ), new BettingMoney(10000));

        Dealer dealer = new Dealer(Cards.of(
                Card.of(Denomination.KING, Shape.CLUBS),
                Card.of(Denomination.SEVEN, Shape.CLUBS)
        ));

        // when
        GameResult gameResult = GameResult.getGameResult(dealer, Arrays.asList(gamer1));

        // then
        assertThat(gameResult.findProfitByPlayer(gamer1)).isEqualTo(15000);
        assertThat(gameResult.findProfitByPlayer(dealer)).isEqualTo(-15000);
    }

    @DisplayName("플레이어가 블랙잭이며 딜러도 블랙잭일 때")
    @Test
    void 게임_결과_수익_확인2() {
        Gamer gamer1 = new Gamer(new Name("pobi"), Cards.of(
                Card.of(Denomination.ACE, Shape.CLUBS),
                Card.of(Denomination.JACK, Shape.CLUBS)
        ), new BettingMoney(10000));

        Dealer dealer = new Dealer(Cards.of(
                Card.of(Denomination.ACE, Shape.DIAMONDS),
                Card.of(Denomination.KING, Shape.CLUBS)
        ));

        // when
        GameResult gameResult = GameResult.getGameResult(dealer, Arrays.asList(gamer1));

        // then
        assertThat(gameResult.findProfitByPlayer(gamer1)).isEqualTo(0);
        assertThat(gameResult.findProfitByPlayer(dealer)).isEqualTo(0);
    }

    @DisplayName("플레이어는 블랙잭이 아니지만 딜러가 블랙잭일 때")
    @Test
    void 게임_결과_수익_확인3() {
        Gamer gamer1 = new Gamer(new Name("pobi"), Cards.of(
                Card.of(Denomination.NINE, Shape.CLUBS),
                Card.of(Denomination.JACK, Shape.CLUBS)
        ), new BettingMoney(10000));

        Dealer dealer = new Dealer(Cards.of(
                Card.of(Denomination.ACE, Shape.DIAMONDS),
                Card.of(Denomination.KING, Shape.CLUBS)
        ));

        // when
        GameResult gameResult = GameResult.getGameResult(dealer, Arrays.asList(gamer1));

        // then
        assertThat(gameResult.findProfitByPlayer(gamer1)).isEqualTo(-10000);
        assertThat(gameResult.findProfitByPlayer(dealer)).isEqualTo(10000);
    }

    @DisplayName("둘 다 블랙잭이 아닌 상태에서 게이머가 버스트일 때")
    @Test
    void 게임_결과_수익_확인4() {
        Gamer gamer1 = new Gamer(new Name("pobi"), Cards.of(
                Card.of(Denomination.NINE, Shape.DIAMONDS),
                Card.of(Denomination.KING, Shape.CLUBS),
                Card.of(Denomination.SEVEN, Shape.CLUBS)
        ), new BettingMoney(10000));

        Dealer dealer = new Dealer(Cards.of(
                Card.of(Denomination.NINE, Shape.CLUBS),
                Card.of(Denomination.JACK, Shape.CLUBS)
        ));

        // when
        GameResult gameResult = GameResult.getGameResult(dealer, Arrays.asList(gamer1));

        // then
        assertThat(gameResult.findProfitByPlayer(gamer1)).isEqualTo(-10000);
        assertThat(gameResult.findProfitByPlayer(dealer)).isEqualTo(10000);
    }

    @DisplayName("둘 다 블랙잭이 아닌 상태에서 딜러가 버스트일 때")
    @Test
    void 게임_결과_수익_확인5() {
        Gamer gamer1 = new Gamer(new Name("pobi"), Cards.of(
                Card.of(Denomination.NINE, Shape.CLUBS),
                Card.of(Denomination.JACK, Shape.CLUBS)
        ), new BettingMoney(10000));

        Dealer dealer = new Dealer(Cards.of(
                Card.of(Denomination.NINE, Shape.DIAMONDS),
                Card.of(Denomination.KING, Shape.CLUBS),
                Card.of(Denomination.SEVEN, Shape.CLUBS)
        ));

        // when
        GameResult gameResult = GameResult.getGameResult(dealer, Arrays.asList(gamer1));

        // then
        assertThat(gameResult.findProfitByPlayer(gamer1)).isEqualTo(10000);
        assertThat(gameResult.findProfitByPlayer(dealer)).isEqualTo(-10000);
    }

    @DisplayName("둘 다 블랙잭이 아닌 상태에서 딜러 점수가 더 높을 때")
    @Test
    void 게임_결과_수익_확인6() {
        Gamer gamer1 = new Gamer(new Name("pobi"), Cards.of(
                Card.of(Denomination.TWO, Shape.CLUBS),
                Card.of(Denomination.JACK, Shape.CLUBS)
        ), new BettingMoney(10000));

        Dealer dealer = new Dealer(Cards.of(
                Card.of(Denomination.NINE, Shape.DIAMONDS),
                Card.of(Denomination.KING, Shape.CLUBS)
        ));

        // when
        GameResult gameResult = GameResult.getGameResult(dealer, Arrays.asList(gamer1));

        // then
        assertThat(gameResult.findProfitByPlayer(gamer1)).isEqualTo(-10000);
        assertThat(gameResult.findProfitByPlayer(dealer)).isEqualTo(10000);
    }

    @DisplayName("둘 다 블랙잭이 아닌 상태에서 점수가 같을 때")
    @Test
    void 게임_결과_수익_확인7() {
        Gamer gamer1 = new Gamer(new Name("pobi"), Cards.of(
                Card.of(Denomination.NINE, Shape.CLUBS),
                Card.of(Denomination.JACK, Shape.CLUBS)
        ), new BettingMoney(10000));

        Dealer dealer = new Dealer(Cards.of(
                Card.of(Denomination.NINE, Shape.DIAMONDS),
                Card.of(Denomination.JACK, Shape.DIAMONDS)
        ));

        // when
        GameResult gameResult = GameResult.getGameResult(dealer, Arrays.asList(gamer1));

        // then
        assertThat(gameResult.findProfitByPlayer(gamer1)).isEqualTo(0);
        assertThat(gameResult.findProfitByPlayer(dealer)).isEqualTo(0);
    }

    @DisplayName("둘 다 블랙잭이 아닌 상태에서 게이머의 점수가 더 높을 때")
    @Test
    void 게임_결과_수익_확인8() {
        Gamer gamer1 = new Gamer(new Name("pobi"), Cards.of(
                Card.of(Denomination.NINE, Shape.CLUBS),
                Card.of(Denomination.JACK, Shape.CLUBS)
        ), new BettingMoney(10000));

        Dealer dealer = new Dealer(Cards.of(
                Card.of(Denomination.NINE, Shape.DIAMONDS),
                Card.of(Denomination.SEVEN, Shape.CLUBS)
        ));

        // when
        GameResult gameResult = GameResult.getGameResult(dealer, Arrays.asList(gamer1));

        // then
        assertThat(gameResult.findProfitByPlayer(gamer1)).isEqualTo(10000);
        assertThat(gameResult.findProfitByPlayer(dealer)).isEqualTo(-10000);
    }
}


