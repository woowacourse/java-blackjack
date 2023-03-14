package blackjackgame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.*;

class ResultTest {
    private final Card ten = new Card(Symbol.CLOVER, CardValue.KING);
    private final Card nine = new Card(Symbol.CLOVER, CardValue.NINE);
    private final Card ace = new Card(Symbol.CLOVER, CardValue.ACE);
    private final Card two = new Card(Symbol.CLOVER, CardValue.TWO);
    private final BettingMoney thousandBetting = BettingMoney.of(1000);
    private Guest blackjackWin;
    private Guest win;
    private Guest lose;
    private Guest draw;
    private Dealer dealer;

    @BeforeEach
    void makePlayer() {
        blackjackWin = new Guest(new Name("blackjack"), new Hand(ten, ace), thousandBetting);

        win = new Guest(new Name("win"), new Hand(ten, nine), thousandBetting);
        win.addCard(two);

        lose = new Guest(new Name("lose"), new Hand(ten, ace), thousandBetting);
        lose.addCard(two);

        draw = new Guest(new Name("draw"), new Hand(ten, nine), thousandBetting);
        draw.addCard(ace);

        dealer = new Dealer(new Hand(ten, nine));
        dealer.addCard(ace);
    }

    @DisplayName("Guest들의 올바른 게임 결과가 나오는지 확인한다")
    @Test
    void Should_EqualExpectedResult_When_GenerateGuestsResult() {
        Map<Guest, GameOutcome> guestsResult = Result.getGuestsResult(List.of(blackjackWin, win, lose, draw), dealer);

        assertThat(guestsResult.get(blackjackWin)).isEqualTo(GameOutcome.BLACKJACK_WIN);
        assertThat(guestsResult.get(win)).isEqualTo(GameOutcome.WIN);
        assertThat(guestsResult.get(lose)).isEqualTo(GameOutcome.LOSE);
        assertThat(guestsResult.get(draw)).isEqualTo(GameOutcome.DRAW);
    }

    @DisplayName("딜러의 올바른 게임 결과가 나오는지 확인한다")
    @Test
    void Should_EqualExpectedResult_When_GenerateDealerResult() {
        Map<GameOutcome, Integer> dealerResult = Result.getDealerResult(List.of(blackjackWin, win, lose, draw), dealer);

        assertThat(dealerResult.get(GameOutcome.WIN)).isEqualTo(1);
        assertThat(dealerResult.get(GameOutcome.LOSE)).isEqualTo(2);
        assertThat(dealerResult.get(GameOutcome.DRAW)).isEqualTo(1);
    }

    @DisplayName("플레이어들의 올바른 수익금이 계산되는지 확인한다")
    @Test
    void Should_EqualExpectedResult_When_GenerateResult() {
        Map<String, Integer> playersRevenue = Result.getPlayersRevenue(List.of(blackjackWin, win, lose, draw), dealer);

        assertThat(playersRevenue.get("딜러")).isEqualTo(-1500);
        assertThat(playersRevenue.get("blackjack")).isEqualTo(1500);
        assertThat(playersRevenue.get("win")).isEqualTo(1000);
        assertThat(playersRevenue.get("lose")).isEqualTo(-1000);
        assertThat(playersRevenue.get("draw")).isEqualTo(0);
    }
}
