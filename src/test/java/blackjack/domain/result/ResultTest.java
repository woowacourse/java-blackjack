package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
    @DisplayName("플레이어가 이긴 경우(플레이어만 블랙잭)")
    void playerBlackjackTest() {
        player.drawCard(new Card(Denomination.ACE, Suit.CLUBS));
        player.drawCard(new Card(Denomination.JACK, Suit.CLUBS));

        Result result = Result.judge(dealer, player);

        assertThat(result).isEqualTo(Result.BLACKJACK);
    }

    @ParameterizedTest
    @DisplayName("플레이어가 진 경우(1. 플레이어가 버스트 2. 딜러보다 점수 낮음)")
    @CsvSource(value = {
        "NINE,TEN,SEVEN",
        "FOUR,TWO,THREE"
    })
    void playerLoseTest(Denomination d1, Denomination d2, Denomination d3) {
        player.drawCard(new Card(d1, Suit.CLUBS));
        player.drawCard(new Card(d2, Suit.CLUBS));
        player.drawCard(new Card(d3, Suit.CLUBS));

        Result result = Result.judge(dealer, player);

        assertThat(result).isEqualTo(Result.LOSE);
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
    @DisplayName("플레이어가 이긴 경우")
    void playerWinTest() {
        player.drawCard(new Card(Denomination.TWO, Suit.DIAMONDS));
        player.drawCard(new Card(Denomination.TEN, Suit.DIAMONDS));

        Result result = Result.judge(dealer, player);

        assertThat(result).isEqualTo(Result.WIN);
    }

    @ParameterizedTest
    @DisplayName("승무패 결과에 따른 수익 계산이 잘되는지 확인")
    @CsvSource(value = {
        "BLACKJACK,1000,1500",
        "LOSE,1000,-1000",
        "DRAW,1000,0",
        "WIN,1000,1000"
    })
    void calculateRevenueTest(Result result, int money, int profit) {
        assertThat(result.calculateRevenue(money))
            .isEqualTo(profit);
    }
}
