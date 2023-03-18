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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PrizeCalculatorTest {

    @Test
    @DisplayName("승리한 유저를 정산기에 전달한다면 수익금을 베팅금 만큼 반환한다.")
    void winProfitTest() {
        //given
        final HashMap<Name, BettingMoney> nameBettingMoney = new HashMap<>();
        final Name pooh = new Name("푸우");
        nameBettingMoney.put(pooh, new BettingMoney(1000));
        final PrizeCalculator prizeCalculator = new PrizeCalculator(nameBettingMoney);

        //when,then
        assertThat(prizeCalculator.getProfit(pooh, Result.WIN)).isEqualTo(1000);
    }

    @Test
    @DisplayName("블랙잭 승리한 유저를 정산기에 전달한다면 수익금을 베팅금 1.5배 만큼 반환한다.")
    void blackJackWinProfitTest() {
        //given
        final HashMap<Name, BettingMoney> nameBettingMoney = new HashMap<>();
        final Name pooh = new Name("푸우");
        nameBettingMoney.put(pooh, new BettingMoney(1000));
        final PrizeCalculator prizeCalculator = new PrizeCalculator(nameBettingMoney);

        //when
        final int profit = prizeCalculator.getProfit(pooh, Result.BLACK_JACK_WIN);

        //then
        assertThat(profit).isEqualTo(1500);
    }

    @Test
    @DisplayName("비긴 유저를 정산기에 전달한다면 수익금은 없는 것이다.")
    void drawProfitTest() {
        //given
        final HashMap<Name, BettingMoney> nameBettingMoney = new HashMap<>();
        final Name pooh = new Name("푸우");
        nameBettingMoney.put(pooh, new BettingMoney(1000));
        final PrizeCalculator prizeCalculator = new PrizeCalculator(nameBettingMoney);

        //when,then
        assertThat(prizeCalculator.getProfit(pooh, Result.DRAW)).isEqualTo(0);
    }

    @Test
    @DisplayName("패배한 유저를 정산기에 전달한다면 잃은 돈은 베팅금 만큼이다.")
    void loseProfitTest() {
        //given
        final HashMap<Name, BettingMoney> nameBettingMoney = new HashMap<>();
        final Name pooh = new Name("푸우");
        nameBettingMoney.put(pooh, new BettingMoney(1000));
        final PrizeCalculator prizeCalculator = new PrizeCalculator(nameBettingMoney);

        //when, then
        assertThat(prizeCalculator.getProfit(pooh, Result.LOSE)).isEqualTo(-1000);
    }

    @ParameterizedTest
    @EnumSource(Result.class)
    @DisplayName("수익 결과 테스트")
    void profitTest(Result result) {
        //given
        final HashMap<Name, BettingMoney> nameBettingMoney = new HashMap<>();

        final Name poohName = new Name("푸우");
        final int bettingValue = 1000;
        nameBettingMoney.put(poohName, new BettingMoney(bettingValue));

        final PrizeCalculator prizeCalculator = new PrizeCalculator(nameBettingMoney);

        final User pooh = new User(poohName, new Cards(List.of(new Card(Shape.HEART, CardNumber.ACE), new Card(Shape.HEART, CardNumber.ACE))));

        final HashMap<Result, List<User>> resultData = new HashMap<>();

        resultData.put(result, List.of(pooh));

        //when
        prizeCalculator.enrollResult(resultData);
        final HashMap<Name, Integer> expectedProfit = new HashMap<>();
        expectedProfit.put(poohName, result.getProfit(bettingValue));

        //then
        assertThat(prizeCalculator.getProfit()).containsAllEntriesOf(expectedProfit);
    }

    @Test
    @DisplayName("딜러 수익 결과 테스트")
    void dealerProfitTest() {
        //given
        final HashMap<Name, BettingMoney> nameBettingMoney = new HashMap<>();

        final Name poohName = new Name("푸우");
        nameBettingMoney.put(poohName, new BettingMoney(1000));

        final PrizeCalculator prizeCalculator = new PrizeCalculator(nameBettingMoney);

        final User pooh = new User(poohName, new Cards(List.of(new Card(Shape.HEART, CardNumber.ACE), new Card(Shape.HEART, CardNumber.ACE))));

        final HashMap<Result, List<User>> resultData = new HashMap<>();

        resultData.put(Result.BLACK_JACK_WIN, List.of(pooh));

        //when
        prizeCalculator.enrollResult(resultData);
        final int dealerProfit = prizeCalculator.getDealerProfit();

        //then
        assertThat(dealerProfit).isEqualTo(-1500);
    }
}
