package domain.game;

import domain.betting.BettingAmount;
import domain.card.Card;
import domain.player.Dealer;
import domain.player.Gambler;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    @Test
    @DisplayName("게임 최종 결과 딜러, 겜블러 동시에 블랙잭")
    void 게임_최종_결과_딜러_겜블러_블랙잭() {
        Dealer dealer = new Dealer(Dealer.DEALER_NAME);
        dealer.addCard(new Card("K", "다이아몬드"));
        dealer.addCard(new Card("A", "다이아몬드"));

        Gambler gambler = new Gambler("pobi", new BettingAmount(10000));
        gambler.addCard(new Card("Q", "다이아몬드"));
        gambler.addCard(new Card("A", "하트"));
        Assertions.assertThat(GameResult.determine(dealer, gambler)).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("게임 최종 결과 겜블러 블랙잭")
    void 게임_최종_결과_겜블러_블랙잭() {
        Dealer dealer = new Dealer(Dealer.DEALER_NAME);
        dealer.addCard(new Card("2", "다이아몬드"));
        dealer.addCard(new Card("A", "다이아몬드"));

        Gambler gambler = new Gambler("pobi", new BettingAmount(10000));
        gambler.addCard(new Card("Q", "다이아몬드"));
        gambler.addCard(new Card("A", "하트"));
        Assertions.assertThat(GameResult.determine(dealer, gambler))
                .isEqualTo(GameResult.BLACK_JACK);
    }

    @Test
    @DisplayName("게임 최종 결과 딜러 승리")
    void 게임_최종_결과_딜러_승() {
        Dealer dealer = new Dealer(Dealer.DEALER_NAME);
        dealer.addCard(new Card("8", "다이아몬드"));
        dealer.addCard(new Card("A", "다이아몬드"));

        Gambler gambler = new Gambler("pobi", new BettingAmount(10000));
        gambler.addCard(new Card("2", "다이아몬드"));
        gambler.addCard(new Card("3", "하트"));
        Assertions.assertThat(GameResult.determine(dealer, gambler)).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("게임 최종 결과 겜블러 승리")
    void 게임_최종_결과_겜블러_승리() {
        Dealer dealer = new Dealer(Dealer.DEALER_NAME);
        dealer.addCard(new Card("3", "다이아몬드"));
        dealer.addCard(new Card("6", "다이아몬드"));

        Gambler gambler = new Gambler("pobi", new BettingAmount(10000));
        gambler.addCard(new Card("9", "다이아몬드"));
        gambler.addCard(new Card("A", "하트"));
        Assertions.assertThat(GameResult.determine(dealer, gambler)).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("블랙잭 시 베팅 금액의 1.5배 수익을 반환한다")
    void 겜블러_블랙잭_수익금액_계산() {
        BettingAmount bettingAmount = new BettingAmount(10000);
        Profit resultProfit = GameResult.BLACK_JACK.calculateProfit(bettingAmount);
        Assertions.assertThat(resultProfit).isEqualTo(new Profit(15000));
    }

    @Test
    @DisplayName("승리 시 베팅 금액의 1배 수익을 반환한다")
    void 겜블러_승리_수익금액_계산() {
        BettingAmount bettingAmount = new BettingAmount(10000);
        Profit resultProfit = GameResult.WIN.calculateProfit(bettingAmount);
        Assertions.assertThat(resultProfit).isEqualTo(new Profit(10000));
    }

    @Test
    @DisplayName("패배 시 베팅 금액의 -1배 수익을 반환한다")
    void 겜블러_패배_수익금액_계산() {
        BettingAmount bettingAmount = new BettingAmount(10000);
        Profit resultProfit = GameResult.LOSE.calculateProfit(bettingAmount);
        Assertions.assertThat(resultProfit).isEqualTo(new Profit(-10000));
    }

    @Test
    @DisplayName("무승부 시 베팅 금액의 0배 수익을 반환한다")
    void 겜블러_블랙잭_무승부_수익금액_계산() {
        BettingAmount bettingAmount = new BettingAmount(10000);
        Profit resultProfit = GameResult.DRAW.calculateProfit(bettingAmount);
        Assertions.assertThat(resultProfit).isEqualTo(new Profit(0));
    }
}
