package domain.result;

import domain.CardsFactory;
import domain.participant.Dealer;
import domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WinLossResultTest {

    @Test
    @DisplayName("딜러보다 플레이어의 총합이 작으면 플레이어는 패배")
    void test1() {
        CardsFactory cardsFactory = new CardsFactory();

        Dealer dealer = new Dealer();
        dealer.receiveCard(cardsFactory.createScore19Cards1());

        Player player = new Player("모루");
        player.receiveCard(cardsFactory.createScore18Cards());

        assertThat(WinLossResult.from(dealer, player)).isEqualTo(WinLossResult.LOSS);
    }

    @Test
    @DisplayName("딜러보다 플레이어의 총합이 크면 플레이어는 승리")
    void test2() {
        CardsFactory cardsFactory = new CardsFactory();

        Dealer dealer = new Dealer();
        dealer.receiveCard(cardsFactory.createScore18Cards());

        Player player = new Player("모루");
        player.receiveCard(cardsFactory.createScore19Cards1());

        assertThat(WinLossResult.from(dealer, player)).isEqualTo(WinLossResult.WIN);
    }

    @Test
    @DisplayName("딜러와 플레이어의 총합이 같으면 무승부")
    void test3() {
        CardsFactory cardsFactory = new CardsFactory();

        Dealer dealer = new Dealer();
        dealer.receiveCard(cardsFactory.createScore19Cards1());

        Player player = new Player("모루");
        player.receiveCard(cardsFactory.createScore19Cards2());

        assertThat(WinLossResult.from(dealer, player)).isEqualTo(WinLossResult.DRAW);
    }

    @Test
    @DisplayName("딜러와 플레이어가 둘다 블랙잭이면 무승부")
    void test4() {
        CardsFactory cardsFactory = new CardsFactory();

        Dealer dealer = new Dealer();
        dealer.receiveCard(cardsFactory.createBlackJackCards1());

        Player player = new Player("모루");
        player.receiveCard(cardsFactory.createBlackJackCards2());

        assertThat(WinLossResult.from(dealer, player)).isEqualTo(WinLossResult.DRAW);
    }

    @Test
    @DisplayName("딜러와 플레이어의 총합이 21로 같은데, 플레이어만 블랙잭이면 플레이어 승리")
    void test5() {
        CardsFactory cardsFactory = new CardsFactory();

        Dealer dealer = new Dealer();
        dealer.receiveCard(cardsFactory.createMaxScoreCards());

        Player player = new Player("모루");
        player.receiveCard(cardsFactory.createBlackJackCards1());

        assertThat(WinLossResult.from(dealer, player)).isEqualTo(WinLossResult.BLACKJACK_WIN);
    }

    @Test
    @DisplayName("딜러와 플레이어의 총합이 21로 같은데, 딜러만 블랙잭이면 플레이어 패배")
    void test6() {
        CardsFactory cardsFactory = new CardsFactory();

        Dealer dealer = new Dealer();
        dealer.receiveCard(cardsFactory.createBlackJackCards1());

        Player player = new Player("모루");
        player.receiveCard(cardsFactory.createMaxScoreCards());

        assertThat(WinLossResult.from(dealer, player)).isEqualTo(WinLossResult.LOSS);
    }

    @Test
    @DisplayName("딜러와 플레이어가 모두 버스트이면, 플레이어 패배")
    void test7() {
        Dealer dealer = new Dealer();
        dealer.applyBustPenalty();

        Player player = new Player("모루");
        player.applyBustPenalty();

        assertThat(WinLossResult.from(dealer, player)).isEqualTo(WinLossResult.LOSS);
    }
}
