package blackjack.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackResultTest {

    @ParameterizedTest
    @CsvSource(value = {"21:20:WIN", "20:21:LOSE", "21:21:DRAW", "22:22:LOSE", "21:22:WIN"}, delimiter = ':')
    @DisplayName("플레이어와 딜러의 점수를 입력 받아, 둘 다 버스트가 아니면서 플레이어가 승리")
    void of(int player, int dealer, String result) {
        BlackJackResult blackJackResult = BlackJackResult.of(player, dealer);
        assertThat(blackJackResult).isEqualTo(BlackJackResult.valueOf(result));
    }

    @ParameterizedTest
    @CsvSource(value = {"21:20:LOSE", "20:21:WIN", "21:21:DRAW", "22:22:WIN", "21:22:LOSE"}, delimiter = ':')
    @DisplayName("Result 결과 값의 반대 결과를 반환한다.")
    void getReverse(int player, int dealer, String result) {
        BlackJackResult blackJackResult = BlackJackResult.of(player, dealer);
        assertThat(blackJackResult.getReverse()).isEqualTo(BlackJackResult.valueOf(result));
    }
}