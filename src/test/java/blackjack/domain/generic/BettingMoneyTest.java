package blackjack.domain.generic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BettingMoneyTest {

    @DisplayName("0보다 작은 금액을 베팅하려하는 경우 Exception")
    @Test
    void ofWhenNegativeAndZero() {
        assertThatThrownBy(() -> BettingMoney.of(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 0원 이상이어야 합니다.");
    }

    @DisplayName("BlackJack 시 1.5배의 이득을 얻는다.")
    @Test
    void getBlackjackMoney() {
        //given
        BettingMoney bettingMoney = BettingMoney.of(1000);

        //when
        BettingMoney blackjackMoney = bettingMoney.getBlackjackMoney();

        //then
        assertThat(blackjackMoney).isEqualTo(BettingMoney.of(1500));
    }

    @DisplayName("평범한 우승시 1배의 이득")
    @Test
    void getMoney() {
        //given
        BettingMoney bettingMoney = BettingMoney.of(1000);

        //when
        BettingMoney blackjackMoney = bettingMoney.getWinMoney();

        //then
        assertThat(blackjackMoney).isEqualTo(BettingMoney.of(1000));
    }

    @DisplayName("패배시 베팅금액을 전부 잃는다.")
    @Test
    void getLoseMoney() {
        //given
        BettingMoney bettingMoney = BettingMoney.of(1000);

        //when
        BettingMoney blackjackMoney = bettingMoney.getLoseMoney();

        //then
        assertThat(blackjackMoney.getMoney()).isEqualTo(-1000);
    }
}