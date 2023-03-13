package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Shape;
import blackjack.domain.game.Result;
import blackjack.domain.user.Name;
import blackjack.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PrizeCalculatorTest {

    @Test
    @DisplayName("승리한 유저를 정산기에 전달한다면 수익금을 베팅금 만큼 반환한다.")
    void winProfitTest() {
        //given
        final HashMap<Name, BettingMoney> nameStake = new HashMap<>();
        final Name pooh = new Name("푸우");
        nameStake.put(pooh, new BettingMoney(1000));
        final PrizeCalculator prizeCalculator = new PrizeCalculator(nameStake);

        //when,then
        assertThat(prizeCalculator.getProfit(pooh, Result.WIN)).isEqualTo(1000);
    }

    @Test
    @DisplayName("블랙잭 승리한 유저를 정산기에 전달한다면 수익금을 베팅금 1.5배 만큼 반환한다.")
    void blackJackWinProfitTest() {
        //given
        final HashMap<Name, BettingMoney> nameStake = new HashMap<>();
        final Name pooh = new Name("푸우");
        nameStake.put(pooh, new BettingMoney(1000));
        final PrizeCalculator prizeCalculator = new PrizeCalculator(nameStake);

        //when,then
        assertThat(prizeCalculator.getProfit(pooh, Result.BLACK_JACK_WIN)).isEqualTo(1500);
    }

    @Test
    @DisplayName("비긴 유저를 정산기에 전달한다면 수익금은 없는 것이다.")
    void drawProfitTest() {
        //given
        final HashMap<Name, BettingMoney> nameStake = new HashMap<>();
        final Name pooh = new Name("푸우");
        nameStake.put(pooh, new BettingMoney(1000));
        final PrizeCalculator prizeCalculator = new PrizeCalculator(nameStake);

        //when,then
        assertThat(prizeCalculator.getProfit(pooh, Result.DRAW)).isEqualTo(0);
    }

    @Test
    @DisplayName("패배한 유저를 정산기에 전달한다면 잃은 돈은 베팅금 만큼이다.")
    void loseProfitTest() {
        //given
        final HashMap<Name, BettingMoney> nameStake = new HashMap<>();
        final Name pooh = new Name("푸우");
        nameStake.put(pooh, new BettingMoney(1000));
        final PrizeCalculator prizeCalculator = new PrizeCalculator(nameStake);

        //when, then
        assertThat(prizeCalculator.getProfit(pooh, Result.LOSE)).isEqualTo(-1000);
    }

    @Test
    @DisplayName("수익 결과 테스트")
    void profitTest() {
        //given
        final HashMap<Name, BettingMoney> nameStake = new HashMap<>();

        final Name poohName = new Name("푸우");
        nameStake.put(poohName, new BettingMoney(1000));

        final Name hupkName = new Name("헙크");
        nameStake.put(hupkName, new BettingMoney(2000));

        final Name youngName = new Name("영이");
        nameStake.put(youngName, new BettingMoney(3000));

        final Name nuguName = new Name("누구");
        nameStake.put(nuguName, new BettingMoney(4000));

        final PrizeCalculator prizeCalculator = new PrizeCalculator(nameStake);

        final User pooh = new User(poohName, new Cards(List.of(new Card(Shape.HEART, CardNumber.ACE), new Card(Shape.HEART, CardNumber.ACE))));
        final User hupk = new User(hupkName, new Cards(List.of(new Card(Shape.HEART, CardNumber.ACE), new Card(Shape.HEART, CardNumber.ACE))));
        final User youngE = new User(youngName, new Cards(List.of(new Card(Shape.HEART, CardNumber.ACE), new Card(Shape.HEART, CardNumber.ACE))));
        final User nugu = new User(nuguName, new Cards(List.of(new Card(Shape.HEART, CardNumber.ACE), new Card(Shape.HEART, CardNumber.ACE))));

        final HashMap<Result, List<User>> resultData = new HashMap<>();

        resultData.put(Result.BLACK_JACK_WIN, List.of(pooh));
        resultData.put(Result.WIN, List.of(hupk));
        resultData.put(Result.LOSE, List.of(youngE));
        resultData.put(Result.DRAW, List.of(nugu));

        //when
        prizeCalculator.enrollResult(resultData);
        final HashMap<Name, Integer> expectedProfit = new HashMap<>();
        expectedProfit.put(poohName, 1500);
        expectedProfit.put(hupkName, 2000);
        expectedProfit.put(youngName, -3000);
        expectedProfit.put(nuguName, 0);

        //then
        assertThat(prizeCalculator.getProfit()).containsAllEntriesOf(expectedProfit);
    }

    @Test
    @DisplayName("딜러 수익 결과 테스트")
    void dealerProfitTest() {
        //given
        final HashMap<Name, BettingMoney> nameStake = new HashMap<>();

        final Name poohName = new Name("푸우");
        nameStake.put(poohName, new BettingMoney(1000));

        final Name hupkName = new Name("헙크");
        nameStake.put(hupkName, new BettingMoney(2000));

        final Name youngName = new Name("영이");
        nameStake.put(youngName, new BettingMoney(3000));

        final Name nuguName = new Name("누구");
        nameStake.put(nuguName, new BettingMoney(4000));

        final PrizeCalculator prizeCalculator = new PrizeCalculator(nameStake);

        final User pooh = new User(poohName, new Cards(List.of(new Card(Shape.HEART, CardNumber.ACE), new Card(Shape.HEART, CardNumber.ACE))));
        final User hupk = new User(hupkName, new Cards(List.of(new Card(Shape.HEART, CardNumber.ACE), new Card(Shape.HEART, CardNumber.ACE))));
        final User youngE = new User(youngName, new Cards(List.of(new Card(Shape.HEART, CardNumber.ACE), new Card(Shape.HEART, CardNumber.ACE))));
        final User nugu = new User(nuguName, new Cards(List.of(new Card(Shape.HEART, CardNumber.ACE), new Card(Shape.HEART, CardNumber.ACE))));

        final HashMap<Result, List<User>> resultData = new HashMap<>();

        resultData.put(Result.BLACK_JACK_WIN, List.of(pooh));
        resultData.put(Result.WIN, List.of(hupk));
        resultData.put(Result.LOSE, List.of(youngE));
        resultData.put(Result.DRAW, List.of(nugu));

        //when
        prizeCalculator.enrollResult(resultData);
        final int dealerProfit = prizeCalculator.getDealerProfit();

        //then
        assertThat(dealerProfit).isEqualTo(-500);
    }
}
