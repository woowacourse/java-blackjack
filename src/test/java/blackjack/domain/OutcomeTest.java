package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OutcomeTest {

    //@formatter:off
    /**
     * 카드 저장 순서
     * Ace카드 HEART, SPADE, CLOVER, DIAMOND 순서대로 4장
     * 2카드 HEART, SPADE, CLOVER, DIAMOND 순서대로 4장
     * ...
     * KING카드 HEART, SPADE, CLOVER, DIAMOND 순서대로 4장
     *
     * 카드 pop 순서는 카드 저장 순서의 역순이다.
     */
    //@formatter:on
    @DisplayName("플레이어가 버스트한 경우 딜러의 패와 관계없이 플레이어의 수익은 (-배팅금액)이다.")
    @Test
    void loseBettingMoneyWhenPlayerBust() {
        final Player player = Player.of("pobi", 10000);
        final Dealer dealer = Dealer.from(new TestDeckFactory().create());
        player.draw(dealer.drawPlayerCard());
        player.draw(dealer.drawPlayerCard());
        player.draw(dealer.drawPlayerCard());
        dealer.draw();
        dealer.draw();
        dealer.draw();

        final Money playerProfit = Outcome.calculatePlayerProfit(dealer, player);

        assertThat(playerProfit).isEqualTo(new Money(-10000));
    }

    @DisplayName("딜러만 버스트했다면 플레이어는 배팅금액만큼 수익을 얻는다.")
    @Test
    void earnProfitWhenDealerOnlyBust() {
        final Player player = Player.of("pobi", 10000);
        final Dealer dealer = Dealer.from(new TestDeckFactory().create());
        player.draw(dealer.drawPlayerCard());
        dealer.draw();
        dealer.draw();
        dealer.draw();

        final Money playerProfit = Outcome.calculatePlayerProfit(dealer, player);

        assertThat(playerProfit).isEqualTo(new Money(10000));
    }

    @DisplayName("플레이어와 딜러가 모두 블랙잭인 경우, 플레이어의 수익은 0원이다.")
    @Test
    void NoProfitWhenBlackjackPush() {
        final Player player = Player.of("pobi", 10000);
        final Dealer dealer = Dealer.from(new TestDeckFactory().create());
        player.draw(dealer.drawPlayerCard());
        dealer.draw();
        for (int i = 0; i < 48; i++) {
            dealer.drawPlayerCard();
        }
        player.draw(dealer.drawPlayerCard());
        dealer.draw();

        final Money playerProfit = Outcome.calculatePlayerProfit(dealer, player);

        assertThat(playerProfit).isEqualTo(new Money(0));
    }

    @DisplayName("플레이어만 블랙잭이라면 배팅금액의 1.5배만큼의 수익을 얻는다.")
    @Test
    void earnProfitWhenPlayerOnlyBlackJack() {
        final Player player = Player.of("pobi", 10000);
        final Dealer dealer = Dealer.from(new TestDeckFactory().create());
        player.draw(dealer.drawPlayerCard());
        dealer.draw();
        for (int i = 0; i < 48; i++) {
            dealer.drawPlayerCard();
        }
        player.draw(dealer.drawPlayerCard());

        final Money playerProfit = Outcome.calculatePlayerProfit(dealer, player);

        assertThat(playerProfit).isEqualTo(new Money(15000));
    }

    @DisplayName("딜러만 블랙잭이라면 플레이어의 수익은 (-배팅금액)이다.")
    @Test
    void loseWhenDealerOnlyBlackjack() {
        final Player player = Player.of("pobi", 10000);
        final Dealer dealer = Dealer.from(new TestDeckFactory().create());
        player.draw(dealer.drawPlayerCard());
        dealer.draw();
        for (int i = 0; i < 48; i++) {
            dealer.drawPlayerCard();
        }
        dealer.draw();

        final Money playerProfit = Outcome.calculatePlayerProfit(dealer, player);

        assertThat(playerProfit).isEqualTo(new Money(-10000));
    }

    @DisplayName("플레이어가 버스트 되지 않고 점수가 딜러보다 높으면 플레이어는 배팅금액만큼 수익을 얻는다.")
    @Test
    void earnProfitWhenPlayerWin() {
        final Player player = Player.of("pobi", 10000);
        final Dealer dealer = Dealer.from(new TestDeckFactory().create());
        player.draw(dealer.drawPlayerCard());
        player.draw(dealer.drawPlayerCard());
        dealer.draw();

        final Money playerProfit = Outcome.calculatePlayerProfit(dealer, player);

        assertThat(playerProfit).isEqualTo(new Money(10000));
    }

    @DisplayName("딜러가 버스트 되지 않고 플레이어 점수가 딜러보다 낮으면 플레이어의 수익은 (-배팅금액)이다.")
    @Test
    void loseBettingMoneyWhenDealerWin() {
        final Player player = Player.of("pobi", 10000);
        final Dealer dealer = Dealer.from(new TestDeckFactory().create());
        player.draw(dealer.drawPlayerCard());
        dealer.draw();
        dealer.draw();

        final Money playerProfit = Outcome.calculatePlayerProfit(dealer, player);

        assertThat(playerProfit).isEqualTo(new Money(-10000));
    }

    @DisplayName("아무도 버스트가 아닐 때, 플레이어 점수와 딜러의 점수가 같으면 플레이어의 수익은 0원이다.")
    @Test
    void pushWhenSame() {
        final Player player = Player.of("pobi", 10000);
        final Dealer dealer = Dealer.from(new TestDeckFactory().create());
        player.draw(dealer.drawPlayerCard());
        player.draw(dealer.drawPlayerCard());
        dealer.draw();
        dealer.draw();

        final Money playerProfit = Outcome.calculatePlayerProfit(dealer, player);

        assertThat(playerProfit).isEqualTo(new Money(0));
    }

    @DisplayName("플레이어의 수익 총합에 -를 붙이면 딜러의 수익이다.")
    @Test
    void calculateDealerProfit() {
        final List<Money> playerProfits = List.of(new Money(10000), new Money(-20000));

        final Money dealerProfit = Outcome.calculateDealerProfit(playerProfits);

        assertThat(dealerProfit.value()).isEqualTo(10000);
    }
}
