package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Shape;
import blackjack.domain.participants.Betting;
import blackjack.domain.participants.Hands;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Result;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingTest {

    @Test
    @DisplayName("게임을 이긴 경우 수익금은 베팅 금액과 동일하다.")
    void bettingWinTest() {
        Betting betting = new Betting();
        Player takan = new Player(new Name("타칸"));
        Profit profit = new Profit(1000);
        Hands hands = new Hands(List.of(
                new Card(Shape.HEART, Rank.ACE),
                new Card(Shape.HEART, Rank.TWO)
        ));

        takan.receiveHands(hands);
        betting.bet(takan, profit);
        betting.calculateProfit(takan, Result.WIN);
        Profit result = betting.getProfit(takan);

        Assertions.assertThat(profit.getProfit()).isEqualTo(result.getProfit());
    }

    @Test
    @DisplayName("블랙잭으로 게임을 이긴 경우 수익금은 베팅 금액의 1.5배이다.")
    void bettingWinBlackjackTest() {
        Betting betting = new Betting();
        Player takan = new Player(new Name("타칸"));
        Hands hands = new Hands(List.of(
                new Card(Shape.HEART, Rank.KING),
                new Card(Shape.HEART, Rank.ACE)
        ));

        takan.receiveHands(hands);
        Profit profit = new Profit(1000);
        betting.bet(takan, profit);
        betting.calculateProfit(takan, Result.WIN);
        Profit result = betting.getProfit(takan);

        Assertions.assertThat(result.getProfit()).isEqualTo(profit.multiple(1.5).getProfit());
    }

    @Test
    @DisplayName("무승부인 경우 수익금은 0원이다.")
    void bettingTieTest() {
        Betting betting = new Betting();
        Player takan = new Player(new Name("타칸"));
        Hands hands = new Hands(List.of(
                new Card(Shape.HEART, Rank.ACE),
                new Card(Shape.HEART, Rank.TWO)
        ));

        takan.receiveHands(hands);
        betting.bet(takan, new Profit(1000));
        betting.calculateProfit(takan, Result.TIE);
        Profit result = betting.getProfit(takan);

        Assertions.assertThat(result.getProfit()).isEqualTo(new Profit(0).getProfit());
    }

    @Test
    @DisplayName("패배인 경우 수익금은 베팅 금액의 음수값과 같다.")
    void bettingLoseTest() {
        Betting betting = new Betting();
        Player takan = new Player(new Name("타칸"));
        Profit profit = new Profit(1000);
        Hands hands = new Hands(List.of(
                new Card(Shape.HEART, Rank.ACE),
                new Card(Shape.HEART, Rank.TWO)
        ));

        takan.receiveHands(hands);
        betting.bet(takan, profit);
        betting.calculateProfit(takan, Result.LOSE);
        Profit result = betting.getProfit(takan);

        Assertions.assertThat(result.getProfit()).isEqualTo(profit.inverse().getProfit());
    }
}
