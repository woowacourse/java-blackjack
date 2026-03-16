package domain.game;

import domain.betting.BettingAmount;
import domain.card.Card;
import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Gamblers;
import java.math.BigDecimal;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamblersGameResultTest {

    @Test
    @DisplayName("딜러가 21이하의 결과값일 때 블랙잭, 패배, 동점 참여자들의 수익을 정산")
    void 딜러_버스트_아닐_경우_블랙잭_패배_동점_참여자들_수익_정산() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card("K", "하트"));
        dealer.addCard(new Card("Q", "하트"));

        Gambler pobi = new Gambler("pobi", new BettingAmount(BigDecimal.valueOf(10000)));
        pobi.addCard(new Card("A", "스페이드"));
        pobi.addCard(new Card("J", "클로버"));

        Gambler coco = new Gambler("coco", new BettingAmount(BigDecimal.valueOf(20000)));
        coco.addCard(new Card("7", "하트"));
        coco.addCard(new Card("Q", "스페이드"));

        Gambler kaiya = new Gambler("kaiya", new BettingAmount(BigDecimal.valueOf(30000)));
        kaiya.addCard(new Card("J", "하트"));
        kaiya.addCard(new Card("Q", "다이아몬드"));

        Gamblers gamblers = new Gamblers(List.of(pobi, coco, kaiya));

        // when
        GamblersGameResult gameResult = new GamblersGameResult(dealer, gamblers);

        // then
        Assertions.assertThat(
                gameResult.getDealerProfit().getProfit()
                        .compareTo(BigDecimal.valueOf(5000))).isEqualTo(0);
        Assertions.assertThat(
                gameResult.getMatchProfits("pobi").getProfit()
                        .compareTo(BigDecimal.valueOf(15000))).isEqualTo(0);
        Assertions.assertThat(gameResult.getMatchProfits("coco").getProfit()
                .compareTo(BigDecimal.valueOf(-20000))).isEqualTo(0);
        Assertions.assertThat(
                gameResult.getMatchProfits("kaiya").getProfit()
                        .compareTo(BigDecimal.ZERO)).isEqualTo(0);
    }

    @Test
    @DisplayName("딜러가 블랙잭일 때 블랙잭, 버스트, 패배 참여자들의 수익을 정산")
    void 딜러_블랙잭인_경우_블랙잭_버스트_패배_참여자_수익_정산() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card("A", "하트"));
        dealer.addCard(new Card("Q", "하트"));

        Gambler pobi = new Gambler("pobi", new BettingAmount(BigDecimal.valueOf(10000)));
        pobi.addCard(new Card("A", "스페이드"));
        pobi.addCard(new Card("J", "클로버"));

        Gambler coco = new Gambler("coco", new BettingAmount(BigDecimal.valueOf(20000)));
        coco.addCard(new Card("7", "하트"));
        coco.addCard(new Card("Q", "스페이드"));
        coco.addCard(new Card("J", "스페이드"));

        Gambler kaiya = new Gambler("kaiya", new BettingAmount(BigDecimal.valueOf(30000)));
        kaiya.addCard(new Card("4", "하트"));
        kaiya.addCard(new Card("Q", "다이아몬드"));

        Gamblers gamblers = new Gamblers(List.of(pobi, coco, kaiya));

        // when
        GamblersGameResult gameResult = new GamblersGameResult(dealer, gamblers);

        // then
        Assertions.assertThat(
                gameResult.getDealerProfit().getProfit()
                        .compareTo(BigDecimal.valueOf(50000))).isEqualTo(0);
        Assertions.assertThat(
                gameResult.getMatchProfits("pobi").getProfit()
                        .compareTo(BigDecimal.valueOf(0))).isEqualTo(0);
        Assertions.assertThat(gameResult.getMatchProfits("coco").getProfit()
                .compareTo(BigDecimal.valueOf(-20000))).isEqualTo(0);
        Assertions.assertThat(gameResult.getMatchProfits("kaiya").getProfit()
                .compareTo(BigDecimal.valueOf(-30000))).isEqualTo(0);
    }

    @Test
    @DisplayName("딜러가 버스트일 때 블랙잭, 버스트, 승리 참여자들의 수익을 정산")
    void 딜러_버스트인_경우_블랙잭_버스트_승리_참여자_수익_정산() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card("5", "하트"));
        dealer.addCard(new Card("Q", "하트"));
        dealer.addCard(new Card("9", "하트"));

        Gambler pobi = new Gambler("pobi", new BettingAmount(BigDecimal.valueOf(10000)));
        pobi.addCard(new Card("A", "스페이드"));
        pobi.addCard(new Card("J", "클로버"));

        Gambler coco = new Gambler("coco", new BettingAmount(BigDecimal.valueOf(20000)));
        coco.addCard(new Card("4", "다이아몬드"));
        coco.addCard(new Card("Q", "스페이드"));
        coco.addCard(new Card("J", "스페이드"));

        Gambler kaiya = new Gambler("kaiya", new BettingAmount(BigDecimal.valueOf(30000)));
        kaiya.addCard(new Card("4", "하트"));
        kaiya.addCard(new Card("Q", "다이아몬드"));

        Gamblers gamblers = new Gamblers(List.of(pobi, coco, kaiya));

        // when
        GamblersGameResult gameResult = new GamblersGameResult(dealer, gamblers);

        // then
        Assertions.assertThat(
                gameResult.getDealerProfit().getProfit()
                        .compareTo(BigDecimal.valueOf(-25000))).isEqualTo(0);
        Assertions.assertThat(
                gameResult.getMatchProfits("pobi").getProfit()
                        .compareTo(BigDecimal.valueOf(15000))).isEqualTo(0);
        Assertions.assertThat(gameResult.getMatchProfits("coco").getProfit()
                .compareTo(BigDecimal.valueOf(-20000))).isEqualTo(0);
        Assertions.assertThat(gameResult.getMatchProfits("kaiya").getProfit()
                .compareTo(BigDecimal.valueOf(30000))).isEqualTo(0);
    }
}
