package blackjack.domain.money;

import static blackjack.domain.Fixtures.ACE_DIAMOND;
import static blackjack.domain.Fixtures.JACK_DIAMOND;
import static blackjack.domain.Fixtures.KING_DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.player.User;
import blackjack.domain.result.Result;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class BettingMoneyTest {

    @Test
    @DisplayName("배팅 머니 생성을 확인한다.")
    void createBettingMoney() {
        final int expected = 10000;
        BettingMoney bettingMoney = BettingMoney.of(expected);

        final int actual = bettingMoney.getValue();
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {999, 0, -10000})
    @DisplayName("최소 배팅 머니 충족하지 않을 때 예외를 발생시킨다.")
    void validateBettingMoney(final int money) {
        assertThatThrownBy(() ->
                BettingMoney.of(money))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("provideUserAndResultAndBettingMoney")
    @DisplayName("승무패에 따른 유저의 결과를 확인한다.")
    void calculateRevenue(final User user, final Result result, final int expected) {
        final BettingMoney bettingMoney = BettingMoney.of(10000);

        int actual = (int) bettingMoney.calculateRevenue(user, result);
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> provideUserAndResultAndBettingMoney() {
        return Stream.of(
                Arguments.of(new User("pobi", new ArrayList<>(Arrays.asList(JACK_DIAMOND, KING_DIAMOND))), Result.WIN,
                        10000),
                Arguments.of(new User("jun", new ArrayList<>(Arrays.asList(ACE_DIAMOND, KING_DIAMOND))), Result.WIN,
                        15000),
                Arguments.of(new User("jason", new ArrayList<>(Arrays.asList(JACK_DIAMOND, KING_DIAMOND))), Result.DRAW,
                        0),
                Arguments.of(new User("renno", new ArrayList<>(Arrays.asList(JACK_DIAMOND, KING_DIAMOND))), Result.LOSE,
                        -10000)
        );
    }
}
