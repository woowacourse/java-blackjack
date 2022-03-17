package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

class ResultTest {

    private Dealer dealer;
    private Player player;

    @BeforeEach
    void init() {
        dealer = new Dealer();
        dealer.drawCard(new Card(Denomination.TWO, Suit.DIAMONDS));
        dealer.drawCard(new Card(Denomination.EIGHT, Suit.DIAMONDS));
        player = new Player("a");
    }

    @Test
    @DisplayName("플레이어가 진 경우")
    void dealerWinTest() {
        player.drawCard(new Card(Denomination.TWO, Suit.CLUBS));
        player.drawCard(new Card(Denomination.THREE, Suit.DIAMONDS));

        Result result = Result.judge(dealer, player);

        assertThat(result).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("플레이어가 이긴 경우")
    void playerWinTest() {
        player.drawCard(new Card(Denomination.TWO, Suit.DIAMONDS));
        player.drawCard(new Card(Denomination.TEN, Suit.DIAMONDS));

        Result result = Result.judge(dealer, player);

        assertThat(result).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("비긴 경우")
    void drawTest() {
        player.drawCard(new Card(Denomination.TWO, Suit.CLUBS));
        player.drawCard(new Card(Denomination.EIGHT, Suit.CLUBS));

        Result result = Result.judge(dealer, player);

        assertThat(result).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("승무패 결과에 따른 수익 계산이 잘되는지 확인")
    void calculateRevenueTest() {
        assertThat(Result.BLACKJACK.calculateRevenue(1000))
            .isEqualTo(1500);
    }
}