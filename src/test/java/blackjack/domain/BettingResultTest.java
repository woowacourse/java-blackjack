package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Shape;
import blackjack.domain.participants.Hands;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.State;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingResultTest {

    @Test
    @DisplayName("게임을 이긴 경우 수익금은 베팅 금액과 동일하다.")
    void bettingWinTest() {
        BettingResult bettingResult = new BettingResult();
        Player takan = new Player(new Name("타칸"));
        Profit profit = new Profit(1000);

        bettingResult.bet(takan, profit);
        Profit result = bettingResult.calculateProfit(takan, State.WIN);

        Assertions.assertThat(profit).isEqualTo(result);
    }

    @Test
    @DisplayName("블랙잭으로 게임을 이긴 경우 수익금은 베팅 금액의 1.5배이다.")
    void bettingWinBlackjackTest() {
        BettingResult bettingResult = new BettingResult();
        Player takan = new Player(new Name("타칸"));
        Hands hands = new Hands(List.of(
                new Card(Shape.HEART, Rank.KING),
                new Card(Shape.HEART, Rank.ACE)
        ));

        takan.receiveHands(hands);
        Profit profit = new Profit(1000);
        bettingResult.bet(takan, profit);
        Profit result = bettingResult.calculateProfit(takan, State.WIN);

        Assertions.assertThat(result).isEqualTo(profit.multiple(1.5));
    }

    @Test
    @DisplayName("무승부인 경우 수익금은 0원이다.")
    void bettingTieTest() {
        BettingResult bettingResult = new BettingResult();
        Player takan = new Player(new Name("타칸"));

        bettingResult.bet(takan, new Profit(1000));
        Profit profit = bettingResult.calculateProfit(takan, State.TIE);

        Assertions.assertThat(profit).isEqualTo(new Profit(0));
    }

    @Test
    @DisplayName("패배인 경우 수익금은 베팅 금액의 음수값과 같다.")
    void bettingLoseTest() {
        BettingResult bettingResult = new BettingResult();
        Player takan = new Player(new Name("타칸"));
        Profit profit = new Profit(1000);

        bettingResult.bet(takan, profit);
        Profit result = bettingResult.calculateProfit(takan, State.LOSE);

        Assertions.assertThat(result).isEqualTo(profit.inverse());
    }
}
