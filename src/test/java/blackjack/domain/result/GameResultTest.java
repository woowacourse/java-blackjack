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

    @DisplayName("플레이어가 블랙잭이며 딜러가 블랙잭이 아닐 때")
    @Test
    void 게임_결과_수익_확인1() {
        Gamer gamer = new Gamer(new Name("pobi"), Cards.of(
                Card.of(Denomination.ACE, Shape.CLUBS),
                Card.of(Denomination.JACK, Shape.CLUBS)
        ), new BettingMoney(10000));

        Dealer dealer = new Dealer(Cards.of(
                Card.of(Denomination.KING, Shape.CLUBS),
                Card.of(Denomination.SEVEN, Shape.CLUBS)
        ));

        // when
        GameResult gameResult = GameResult.of(dealer, Arrays.asList(gamer));

        // then
        assertThat(gameResult.findProfitByPlayer(gamer)).isEqualTo(15000);
        assertThat(gameResult.findProfitByPlayer(dealer)).isEqualTo(-15000);
    }

    @DisplayName("플레이어가 블랙잭이며 딜러도 블랙잭일 때")
    @Test
    void 게임_결과_수익_확인2() {
        Gamer gamer = new Gamer(new Name("pobi"), Cards.of(
                Card.of(Denomination.ACE, Shape.CLUBS),
                Card.of(Denomination.JACK, Shape.CLUBS)
        ), new BettingMoney(10000));

        Dealer dealer = new Dealer(Cards.of(
                Card.of(Denomination.ACE, Shape.DIAMONDS),
                Card.of(Denomination.KING, Shape.CLUBS)
        ));

        // when
        GameResult gameResult = GameResult.of(dealer, Arrays.asList(gamer));

        // then
        assertThat(gameResult.findProfitByPlayer(gamer)).isEqualTo(0);
        assertThat(gameResult.findProfitByPlayer(dealer)).isEqualTo(0);
    }

    @DisplayName("플레이어는 블랙잭이 아니지만 딜러가 블랙잭일 때")
    @Test
    void 게임_결과_수익_확인3() {
        Gamer gamer = new Gamer(new Name("pobi"), Cards.of(
                Card.of(Denomination.NINE, Shape.CLUBS),
                Card.of(Denomination.JACK, Shape.CLUBS)
        ), new BettingMoney(10000));

        Dealer dealer = new Dealer(Cards.of(
                Card.of(Denomination.ACE, Shape.DIAMONDS),
                Card.of(Denomination.KING, Shape.CLUBS)
        ));

        // when
        gamer.stay();
        GameResult gameResult = GameResult.of(dealer, Arrays.asList(gamer));

        // then
        assertThat(gameResult.findProfitByPlayer(gamer)).isEqualTo(-10000);
        assertThat(gameResult.findProfitByPlayer(dealer)).isEqualTo(10000);
    }

    @DisplayName("둘 다 블랙잭이 아닌 상태에서 게이머가 버스트일 때")
    @Test
    void 게임_결과_수익_확인4() {
        Gamer gamer = new Gamer(new Name("pobi"), Cards.of(
                Card.of(Denomination.NINE, Shape.DIAMONDS),
                Card.of(Denomination.KING, Shape.CLUBS)
        ), new BettingMoney(10000));

        Dealer dealer = new Dealer(Cards.of(
                Card.of(Denomination.NINE, Shape.CLUBS),
                Card.of(Denomination.JACK, Shape.CLUBS)
        ));

        // when
        gamer.draw(Card.of(Denomination.FIVE, Shape.CLUBS));
        GameResult gameResult = GameResult.of(dealer, Arrays.asList(gamer));

        // then
        assertThat(gameResult.findProfitByPlayer(gamer)).isEqualTo(-10000);
        assertThat(gameResult.findProfitByPlayer(dealer)).isEqualTo(10000);
    }

    @DisplayName("둘 다 블랙잭이 아닌 상태에서 딜러가 버스트일 때")
    @Test
    void 게임_결과_수익_확인5() {
        Gamer gamer = new Gamer(new Name("pobi"), Cards.of(
                Card.of(Denomination.NINE, Shape.CLUBS),
                Card.of(Denomination.JACK, Shape.CLUBS)
        ), new BettingMoney(10000));

        Dealer dealer = new Dealer(Cards.of(
                Card.of(Denomination.NINE, Shape.DIAMONDS),
                Card.of(Denomination.SIX, Shape.CLUBS)
        ));

        // when
        gamer.stay();
        dealer.draw(Card.of(Denomination.JACK, Shape.CLUBS));
        GameResult gameResult = GameResult.of(dealer, Arrays.asList(gamer));

        // then
        assertThat(gameResult.findProfitByPlayer(gamer)).isEqualTo(10000);
        assertThat(gameResult.findProfitByPlayer(dealer)).isEqualTo(-10000);
    }

    @DisplayName("둘 다 블랙잭이 아닌 상태에서 딜러 점수가 더 높을 때")
    @Test
    void 게임_결과_수익_확인6() {
        Gamer gamer = new Gamer(new Name("pobi"), Cards.of(
                Card.of(Denomination.TWO, Shape.CLUBS),
                Card.of(Denomination.JACK, Shape.CLUBS)
        ), new BettingMoney(10000));

        Dealer dealer = new Dealer(Cards.of(
                Card.of(Denomination.NINE, Shape.DIAMONDS),
                Card.of(Denomination.KING, Shape.CLUBS)
        ));

        // when
        gamer.stay();
        GameResult gameResult = GameResult.of(dealer, Arrays.asList(gamer));

        // then
        assertThat(gameResult.findProfitByPlayer(gamer)).isEqualTo(-10000);
        assertThat(gameResult.findProfitByPlayer(dealer)).isEqualTo(10000);
    }

    @DisplayName("둘 다 블랙잭이 아닌 상태에서 점수가 같을 때")
    @Test
    void 게임_결과_수익_확인7() {
        Gamer gamer = new Gamer(new Name("pobi"), Cards.of(
                Card.of(Denomination.NINE, Shape.CLUBS),
                Card.of(Denomination.JACK, Shape.CLUBS)
        ), new BettingMoney(10000));

        Dealer dealer = new Dealer(Cards.of(
                Card.of(Denomination.NINE, Shape.DIAMONDS),
                Card.of(Denomination.JACK, Shape.DIAMONDS)
        ));

        // when
        gamer.stay();
        GameResult gameResult = GameResult.of(dealer, Arrays.asList(gamer));

        // then
        assertThat(gameResult.findProfitByPlayer(gamer)).isEqualTo(0);
        assertThat(gameResult.findProfitByPlayer(dealer)).isEqualTo(0);
    }

    @DisplayName("둘 다 블랙잭이 아닌 상태에서 게이머의 점수가 더 높을 때")
    @Test
    void 게임_결과_수익_확인8() {
        Gamer gamer = new Gamer(new Name("pobi"), Cards.of(
                Card.of(Denomination.NINE, Shape.CLUBS),
                Card.of(Denomination.JACK, Shape.CLUBS)
        ), new BettingMoney(10000));

        Dealer dealer = new Dealer(Cards.of(
                Card.of(Denomination.NINE, Shape.DIAMONDS),
                Card.of(Denomination.SEVEN, Shape.CLUBS)
        ));

        // when
        gamer.stay();
        GameResult gameResult = GameResult.of(dealer, Arrays.asList(gamer));

        // then
        assertThat(gameResult.findProfitByPlayer(gamer)).isEqualTo(10000);
        assertThat(gameResult.findProfitByPlayer(dealer)).isEqualTo(-10000);
    }
}


