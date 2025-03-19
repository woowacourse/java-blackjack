package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetTest {
    @DisplayName("amount가 0인 Bet 객체 생성 성공")
    @Test
    void createZeroValueBetTest() {
        Bet bet = Bet.startingBet();

        assertThat(bet).isNotNull();
        assertThat(bet.amount()).isEqualTo(0);
    }

    @DisplayName("캐싱된 Bet 객체 반환 성공")
    @Test
    void returnCachedBetTest() {
        Bet bet = Bet.startingBet();
        Bet initializedBet = Bet.valueOf(9);
        Bet increasedBet = bet.increase(9);

        assertThat(increasedBet).isSameAs(initializedBet);
    }

    @DisplayName("amount가 증가된 Bet 반환 성공")
    @Test
    void increaseBetValueTest() {
        Bet bet = Bet.startingBet();
        int prevAmount = bet.amount();
        Bet increasedBet = bet.increase(10);
        int newAmount = increasedBet.amount();

        assertThat(newAmount).isEqualTo(prevAmount + 10);
    }
}
