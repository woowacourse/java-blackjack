package domain;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    @Test
    @DisplayName("플레이어와 사용자 입력을 받아서 추가 드로우 상태를 반환한다.")
    void distributePlayerCardOrPass() {
        BlackJackGame blackJackGame = new BlackJackGame(List.of("aa", "bb"));

        Assertions.assertThat(blackJackGame.distributePlayerCardOrPass("aa", "y")).isEqualTo(AdditionalDrawStatus.DRAW);
        Assertions.assertThat(blackJackGame.distributePlayerCardOrPass("aa", "n")).isEqualTo(AdditionalDrawStatus.PASS);
    }
}
