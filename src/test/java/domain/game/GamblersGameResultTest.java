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
    @DisplayName("딜러, 참여자들의 수익을 정산")
    void 딜러_참여자들_수익_정산() {
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
        Assertions.assertThat(gameResult.getDealerProfit())
                .isEqualTo(new Profit(BigDecimal.valueOf(50000)));
        Assertions.assertThat(gameResult.getMatchProfits("pobi"))
                .isEqualTo(new Profit(BigDecimal.valueOf(15000)));
        Assertions.assertThat(gameResult.getMatchProfits("coco"))
                .isEqualTo(new Profit(BigDecimal.valueOf(-15000)));
        Assertions.assertThat(gameResult.getMatchProfits("kaiya"))
                .isEqualTo(new Profit(BigDecimal.ZERO));
    }
}
