package domain.user;

import domain.BettingMoney;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
public class PlayerTest {

    @ParameterizedTest
    @CsvSource(value = {"10000,10000", "55550,55550", "3,3"})
    void 베팅금을_정상적으로_반환하는지_테스트(String input, int expected) {
        Player player = new Player("KIM", new BettingMoney(input));
        int result = player.getBettingMoney();

        Assertions.assertThat(result).isEqualTo(expected);
    }
}
