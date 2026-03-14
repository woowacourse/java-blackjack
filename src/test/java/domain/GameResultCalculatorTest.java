package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class GameResultCalculatorTest {

    @Test
    void 플레이어가_버스트인_경우_딜러가_점수_낮아도_승리_확인() {
        Player player = PlayerFixture.createBust("pobi");
        Players players = Players.from(List.of(player));

        Hand dealerHand = HandFixture.createHand(List.of(
                Card.of(CardNumber.Q, CardShape.CLOVER),
                Card.of(CardNumber.FIVE, CardShape.SPADE)));
        Dealer dealer = Dealer.from(dealerHand);

        TotalFinalResult result = GameResultCalculator.checkGameResult(players, dealer);

        FinalResult playerResult = result.getTotalResult().getFirst();
        assertThat(playerResult.getResultType()).isEqualTo(ResultType.LOSE);
    }

    @Test
    void 딜러가_블랙잭인_경우_블랙잭이_아닌_21점_플레이어_패배_확인() {
        Player player = PlayerFixture.createBlackjackScore("pobi");
        Players players = Players.from(List.of(player));

        Dealer dealer = Dealer.from(HandFixture.createBlackjack());

        TotalFinalResult result = GameResultCalculator.checkGameResult(players, dealer);

        FinalResult dealerWinResult = result.getTotalResult().getFirst();
        assertThat(dealerWinResult.getResultType()).isEqualTo(ResultType.LOSE);
    }

    @Test
    void 플레이어_블랙잭_승리_확인() {
        Player player = PlayerFixture.createBlackjack("pobi");
        Players players = Players.from(List.of(player));

        Dealer dealer = Dealer.from(HandFixture.createHand(List.of(
                Card.of(CardNumber.TEN, CardShape.CLOVER),
                Card.of(CardNumber.EIGHT, CardShape.SPADE))));

        TotalFinalResult result = GameResultCalculator.checkGameResult(players, dealer);

        FinalResult playerBlackjack = result.getTotalResult().getFirst();
        assertThat(playerBlackjack.getResultType()).isEqualTo(ResultType.BLACKJACK_WIN);
    }

    @Test
    void 플레이어_딜러_모두_버스트일_경우_플레이어_패배_확인() {
        Player player = PlayerFixture.createBust("pobi");
        Players players = Players.from(List.of(player));

        Dealer dealer = Dealer.from(HandFixture.createBust());

        TotalFinalResult result = GameResultCalculator.checkGameResult(players, dealer);

        FinalResult playerBlackjack = result.getTotalResult().getFirst();
        assertThat(playerBlackjack.getResultType()).isEqualTo(ResultType.LOSE);
    }

    @Test
    void 플레이어_딜러_모두_블랙잭인_경우_무승부_확인() {
        Player player = PlayerFixture.createBlackjack("pobi");
        Players players = Players.from(List.of(player));

        Dealer dealer = Dealer.from(HandFixture.createBlackjack());

        TotalFinalResult result = GameResultCalculator.checkGameResult(players, dealer);

        FinalResult playerBlackjack = result.getTotalResult().getFirst();
        assertThat(playerBlackjack.getResultType()).isEqualTo(ResultType.DRAW);
    }
}