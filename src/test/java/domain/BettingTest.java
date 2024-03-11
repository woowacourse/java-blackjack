package domain;

import domain.participant.PlayerName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BettingTest {
    @DisplayName("플레이어 이름에 맞는 배팅 금액을 반환한다.")
    @Test
    void getBettingTest() {
        // Given
        Betting betting = Betting.getInstance();
        PlayerName test = new PlayerName("test");
        betting.setBetting(test, new Amount("15000"));

        // When
        Amount bettingAmount = betting.getBetting(test);
        int amount = bettingAmount.getAmount();

        // Then
        assertThat(amount).isEqualTo(15000);
    }
}
