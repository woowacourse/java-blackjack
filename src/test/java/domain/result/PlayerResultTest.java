package domain.result;


import static org.assertj.core.api.Assertions.assertThat;
import static result.GameStatus.*;

import card.Card;
import card.CardNumberType;
import card.CardType;
import card.Hand;
import java.util.List;
import participant.value.Money;
import participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import result.PlayerResult;

class PlayerResultTest {
    Hand blackJack = new Hand(List.of(
            new Card(CardNumberType.JACK, CardType.CLOVER),
            new Card(CardNumberType.ACE, CardType.CLOVER)));
    Player blackjackPlayer = new Player("blackJack", blackJack, Money.bet(10000));
    Player winner = new Player("winner", Hand.createEmpty(), Money.bet(20000));
    Player drawer = new Player("drawer", Hand.createEmpty(), Money.bet(20000));
    Player loser = new Player("loser", Hand.createEmpty(), Money.bet(30000));

    @DisplayName("플레이어는 블랙잭으로 승리할 경우 베팅 금액의 1.5배를 얻는다")
    @Test
    void test10() {
        //given
        PlayerResult playerResult = new PlayerResult(blackjackPlayer, BLACKJACK_WIN);

        //when
        Money profit = playerResult.calculateProfit();

        //then
        assertThat(profit).isEqualTo(new Money(15000));
    }

    @DisplayName("플레이어는 승리할 경우 베팅 금액을 얻는다")
    @Test
    void test11() {
        //given
        PlayerResult playerResult = new PlayerResult(winner, WIN);

        //when
        Money profit = playerResult.calculateProfit();

        //then
        assertThat(profit).isEqualTo(new Money(20000));
    }

    @DisplayName("플레이어는 무승부일 경우 베팅 금액을 그대로 돌려받아 수익이 없다")
    @Test
    void test12() {
        //given
        PlayerResult playerResult = new PlayerResult(drawer, DRAW);

        //when
        Money profit = playerResult.calculateProfit();

        //then
        assertThat(profit).isEqualTo(new Money(0));
    }

    @DisplayName("플레이어는 패배할 경우 베팅 금액을 잃는다")
    @Test
    void test13() {
        //given
        PlayerResult playerResult = new PlayerResult(loser, LOSE);

        //when
        Money profit = playerResult.calculateProfit();

        //then
        assertThat(profit).isEqualTo(new Money(-30000));
    }
}
