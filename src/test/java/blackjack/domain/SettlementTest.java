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
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SettlementTest {

    @Test
    @DisplayName("승리한 유저를 정산기에 전달한다면 수익금을 베팅금 만큼 반환한다.")
    void winProfitTest() {
        //given
        final HashMap<Name, Stake> nameStake = new HashMap<>();
        final Name pooh = new Name("푸우");
        nameStake.put(pooh, new Stake(1000));
        final Settlement settlement = new Settlement(nameStake);

        //when,then
        assertThat(settlement.getProfit(pooh, Result.WIN)).isEqualTo(1000);
    }

    @Test
    @DisplayName("블랙잭 승리한 유저를 정산기에 전달한다면 수익금을 베팅금 1.5배 만큼 반환한다.")
    void blackJackWinProfitTest() {
        //given
        final HashMap<Name, Stake> nameStake = new HashMap<>();
        final Name pooh = new Name("푸우");
        nameStake.put(pooh, new Stake(1000));
        final Settlement settlement = new Settlement(nameStake);

        //when,then
        assertThat(settlement.getProfit(pooh, Result.BLACK_JACK_WIN)).isEqualTo(1500);
    }

    @Test
    @DisplayName("비긴 유저를 정산기에 전달한다면 수익금은 없는 것이다.")
    void drawProfitTest() {
        //given
        final HashMap<Name, Stake> nameStake = new HashMap<>();
        final Name pooh = new Name("푸우");
        nameStake.put(pooh, new Stake(1000));
        final Settlement settlement = new Settlement(nameStake);

        //when,then
        assertThat(settlement.getProfit(pooh, Result.DRAW)).isEqualTo(0);
    }

    @Test
    @DisplayName("패배한 유저를 정산기에 전달한다면 잃은 돈은 베팅금 만큼이다.")
    void loseProfitTest() {
        //given
        final HashMap<Name, Stake> nameStake = new HashMap<>();
        final Name pooh = new Name("푸우");
        nameStake.put(pooh, new Stake(1000));
        final Settlement settlement = new Settlement(nameStake);

        //when, then
        assertThat(settlement.getProfit(pooh, Result.LOSE)).isEqualTo(-1000);
    }

    @Test
    @DisplayName("수익 결과 테스트")
    void profitTest() {
        //given
        final HashMap<Name, Stake> nameStake = new HashMap<>();

        final Name poohName = new Name("푸우");
        nameStake.put(poohName, new Stake(1000));

        final Name hupkName = new Name("헙크");
        nameStake.put(hupkName, new Stake(2000));

        final Name youngName = new Name("영이");
        nameStake.put(youngName, new Stake(3000));

        final Name nuguName = new Name("누구");
        nameStake.put(nuguName, new Stake(4000));

        final Settlement settlement = new Settlement(nameStake);

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
        settlement.enrollResult(resultData);
        final HashMap<Name, Integer> expectedProfit = new HashMap<>();
        expectedProfit.put(poohName, 1500);
        expectedProfit.put(hupkName, 2000);
        expectedProfit.put(youngName, -3000);
        expectedProfit.put(nuguName, 0);

        //then
        assertThat(settlement.getProfit()).containsAllEntriesOf(expectedProfit);
    }

    @Test
    @DisplayName("딜러 수익 결과 테스트")
    void dealerProfitTest() {
        //given
        final HashMap<Name, Stake> nameStake = new HashMap<>();

        final Name poohName = new Name("푸우");
        nameStake.put(poohName, new Stake(1000));

        final Name hupkName = new Name("헙크");
        nameStake.put(hupkName, new Stake(2000));

        final Name youngName = new Name("영이");
        nameStake.put(youngName, new Stake(3000));

        final Name nuguName = new Name("누구");
        nameStake.put(nuguName, new Stake(4000));

        final Settlement settlement = new Settlement(nameStake);

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
        settlement.enrollResult(resultData);
        final int dealerProfit = settlement.getDealerProfit();

        //then
        assertThat(dealerProfit).isEqualTo(-500);
    }
}
