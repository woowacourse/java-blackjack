package domain.member;

import domain.card.Card;
import domain.state.Blackjack;
import domain.state.Bust;
import domain.state.Stay;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @DisplayName("플레이어가 블랙잭이고 딜러가 블랙잭이 아니면 베팅 금액의 1.5배 수익을 반환한다")
    @Test
    void calculateProfit_StateIsBlackjack_ReturnAccurateEarning() {
        Money betMoney = new Money(10_000);
        Player player = new Player(new MemberInfo("pobi", new Blackjack(new Hand())), betMoney);
        Dealer dealer = new Dealer(new MemberInfo(new Stay(new Hand().appendCard(Card.from("10", "하트")))));
        int expected = 15_000;

        int profit = player.calculateProfit(dealer.info());

        assertThat(profit).isEqualTo(expected);
    }

    @DisplayName("플레이어가 버스트 상태이면 딜러의 상태와 상관없이 베팅 금액을 모두 잃는다")
    @Test
    void calculateProfit_StateIsBust_ReturnLostEarning() {
        Money betMoney = new Money(10_000);
        Player player = new Player(new MemberInfo("pobi",
                new Bust(new Hand().appendCard(Card.from("10", "하트")))), betMoney);
        Dealer dealer = new Dealer(new MemberInfo(new Stay(new Hand().appendCard(Card.from("2", "클로버")))));
        int expected = -10_000;

        int profit = player.calculateProfit(dealer.info());

        assertThat(profit).isEqualTo(expected);
    }
}
